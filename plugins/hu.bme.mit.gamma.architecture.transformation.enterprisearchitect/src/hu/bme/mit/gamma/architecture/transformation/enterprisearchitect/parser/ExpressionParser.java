package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.parser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

import hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.traceability.ElementTrace;
import hu.bme.mit.gamma.architecture.transformation.errors.ArchitectureException;
import hu.bme.mit.gamma.architecture.transformation.errors.GammaTransformationException;
import hu.bme.mit.gamma.architecture.transformation.traceability.ArchitectureTrace;
import hu.bme.mit.gamma.architecture.transformation.util.TransformationUtils;
import hu.bme.mit.gamma.expression.model.DecimalLiteralExpression;
import hu.bme.mit.gamma.expression.model.DirectReferenceExpression;
import hu.bme.mit.gamma.expression.model.EnumerableTypeDefinition;
import hu.bme.mit.gamma.expression.model.EnumerationTypeDefinition;
import hu.bme.mit.gamma.expression.model.Expression;
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory;
import hu.bme.mit.gamma.expression.model.FunctionDeclaration;
import hu.bme.mit.gamma.expression.model.ParameterDeclaration;
import hu.bme.mit.gamma.expression.model.VariableDeclaration;
import hu.bme.mit.gamma.expression.util.ExpressionEvaluator;
import hu.bme.mit.gamma.expression.util.ExpressionSerializer;
import hu.bme.mit.gamma.statechart.statechart.AsynchronousStatechartDefinition;
import hu.bme.mit.gamma.statechart.statechart.PortEventReference;
import hu.bme.mit.gamma.statechart.statechart.StatechartModelFactory;
import hu.bme.mit.gamma.statechart.interface_.InterfaceModelFactory;
import hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures;
import hu.bme.mit.gamma.statechart.interface_.EventParameterReferenceExpression;
import hu.bme.mit.gamma.statechart.interface_.Package;
import hu.bme.mit.gamma.statechart.interface_.Persistency;

public class ExpressionParser {
	protected AsynchronousStatechartDefinition statechart;
	private String str;
	protected final ArchitectureTrace trace;

	public ExpressionParser(ArchitectureTrace trace) {
		this.trace = trace;
	}

	public Expression eval(String str, AsynchronousStatechartDefinition contextStatechart)
			throws GammaTransformationException {
		if (str.isBlank()) {
			return null;
		}
		this.statechart = contextStatechart;
		this.str = str;
		pos = -1;
		ch = ' ';
		return parse();
	}

	int pos = -1, ch;

	public FunctionDeclaration searchOperation(String operationName, String args) {
		var funStream = ((Package) statechart.eContainer()).getFunctionDeclarations().stream()
				.filter(f -> f.getName().equals(operationName));
		return funStream.findFirst().get();
	}

	public DirectReferenceExpression searchVariable(String varName) throws GammaTransformationException {
		Iterator<VariableDeclaration> varStream = statechart.getVariableDeclarations().stream()
				.filter(f -> f.getName().equals(varName)).iterator();
		var varRef = expFactory.createDirectReferenceExpression();
		if (!varStream.hasNext()) {
			throw new GammaTransformationException(
					"Parser Exception; Variable reference cannot be found '" + varName + "' in '" + str + "'",
					statechart);
		}
		varRef.setDeclaration(varStream.next());
		return varRef;
	}

	public EventParameterReferenceExpression searchFlowProperty(String portName, String eventName)
			throws GammaTransformationException {

		var portIterator = StatechartModelDerivedFeatures.getAllPortsWithInput(statechart).stream()
				.filter(p -> p.getName().matches('^' + TransformationUtils.getGammaName(portName) + ".+(In|Out)$"))
				.iterator();

		if (!portIterator.hasNext()) {
			throw new GammaTransformationException(
					"Parser Exception; Port reference cannot be found '" + portName + "' in '" + str + "'", statechart);
		}
		var port = portIterator.next();

		if (portIterator.hasNext()) {
			throw new GammaTransformationException(
					"Parser Exception; Port reference is ambigous  '" + portName + "' in '" + str + "'");
		}

		var eventIterator = StatechartModelDerivedFeatures.getAllEvents(port).stream()
				.filter(e -> e.getPersistency() == Persistency.PERSISTENT).filter(e -> e.getName().toLowerCase()
						.equals("changeof" + TransformationUtils.getGammaName(eventName.toLowerCase())))
				.iterator();

		if (!eventIterator.hasNext()) {
			throw new GammaTransformationException(
					"Parser Exception; Event reference cannot be found '" + eventName + "' in '" + str + "'",
					statechart);
		}
		var event = eventIterator.next();

		if (eventIterator.hasNext()) {
			throw new GammaTransformationException(
					"Parser Exception; Event reference is ambigous  '" + eventName + "' in '" + str + "'", statechart);
		}

		var paramIterator = event.getParameterDeclarations().stream()
				.filter(p -> p.getName().equals(TransformationUtils.getGammaName(eventName))).iterator();

		if (!paramIterator.hasNext()) {
			throw new GammaTransformationException(
					"Parser Exception; Parameter reference cannot be found '" + eventName + "' in '" + str + "'",
					statechart);
		}

		var param = paramIterator.next();

		if (paramIterator.hasNext()) {
			throw new GammaTransformationException(
					"Parser Exception; Parameter reference is ambigous  '" + eventName + "' in '" + str + "'",
					statechart);
		}

		var ref = ifFactory.createEventParameterReferenceExpression();
		ref.setPort(port);
		ref.setEvent(event);
		ref.setParameter(param);
		return ref;
	}

	ExpressionModelFactory expFactory = ExpressionModelFactory.eINSTANCE;
	StatechartModelFactory sctFactory = StatechartModelFactory.eINSTANCE;
	InterfaceModelFactory ifFactory = InterfaceModelFactory.eINSTANCE;

	void nextChar() {
		ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	}

	boolean eat(int charToEat) {
		while (ch == ' ')
			nextChar();
		if (ch == charToEat) {
			nextChar();
			return true;
		}
		return false;
	}

	Expression parse() throws GammaTransformationException {
		nextChar();
		Expression x = parseExpression();
		if (pos < str.length())
			throw new GammaTransformationException("Unexpected: " + (char) ch,statechart);
		return x;
	}

	// Grammar:
	// expression = term | expression `+` term | expression `-` term
	// term = factor | term `*` factor | term `/` factor
	// factor = `+` factor | `-` factor | `(` expression `)` | number
	// | functionName `(` expression `)` | functionName factor
	// | factor `^` factor

	Expression parseExpression() throws GammaTransformationException {
		Expression x = parseTerm();
		for (;;) {
			if (eat('+')) {
				var mulExp = expFactory.createAddExpression();
				mulExp.getOperands().add(x);
				mulExp.getOperands().add(parseTerm());
				x = mulExp;
			} // multiplication
			else if (eat('-')) {
					var mulExp = expFactory.createDivExpression();
					mulExp.setLeftOperand(x);
					mulExp.setRightOperand(parseTerm());
				x = mulExp;
			} // division
			else if (eat('|')) {
				var orExp = expFactory.createOrExpression();
				orExp.getOperands().add(x);
				orExp.getOperands().add(parseTerm());
				x = orExp;
			} // division
			else if (eat('^')) {
				var xorExp = expFactory.createXorExpression();
				xorExp.getOperands().add(x);
				xorExp.getOperands().add(parseTerm());
				x = xorExp;
			} // division
			else {
				return x;
			}
		}
	}

	Expression parseTerm() throws GammaTransformationException {
		Expression x = parseFactor();
		for (;;) {
			if (eat('*')) {
				var mulExp = expFactory.createMultiplyExpression();
				mulExp.getOperands().add(x);
				mulExp.getOperands().add(parseFactor());
				x = mulExp;
			} else if (eat('&')) {
				var mulExp = expFactory.createAndExpression();
				mulExp.getOperands().add(x);
				mulExp.getOperands().add(parseFactor());
				x = mulExp;
			} else if (eat('/')) {
				var mulExp = expFactory.createDivideExpression();
				mulExp.setLeftOperand(x);
				mulExp.setRightOperand(parseFactor());
				x = mulExp;
			} else if (eat('=')) {
				var mulExp = expFactory.createEqualityExpression();
				mulExp.setLeftOperand(x);
				mulExp.setRightOperand(parseFactor());
				x = mulExp;
			} else if (eat('<')) {
				var mulExp = expFactory.createLessExpression();
				mulExp.setLeftOperand(x);
				mulExp.setRightOperand(parseFactor());
				x = mulExp;
			} else if (eat('>')) {
				var mulExp = expFactory.createGreaterExpression();
				mulExp.setLeftOperand(x);
				mulExp.setRightOperand(parseFactor());
				x = mulExp;
			} else {
				return x;
			}

		}
	}

	boolean isVarChar(char ch) {
		return isVarChar((int) ch);
	}

	boolean isVarChar(int ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_' || (ch >= '0' && ch <= '9');
	}

	Expression parseFactor() throws GammaTransformationException {

		if (eat('+')) {
			var plus = expFactory.createUnaryPlusExpression();
			plus.setOperand(parseFactor());
			return plus;// unary plus
		}

		if (eat('-')) {
			var minus = expFactory.createUnaryMinusExpression();
			minus.setOperand(parseFactor());
			return minus;// unary minus
		}

		if (eat('~')) {
			var minus = expFactory.createNotExpression();
			minus.setOperand(parseFactor());
			return minus;// unary minus
		}

		if (eat('!')) {
			var minus = expFactory.createNotExpression();
			minus.setOperand(parseFactor());
			return minus;// unary minus
		}

		Expression x = null;
		int startPos = this.pos;
		if (eat('(')) { // parentheses
			x = parseExpression();
			if (!eat(')')) {
				throw new GammaTransformationException("Parser Exception; Missing ')' in " + str + " at " + pos,statechart);
			}
		}
		/*
		 * else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers while ((ch >=
		 * '0' && ch <= '9') || ch == '.') nextChar(); x =
		 * expFactory.createDecimalLiteralExpression(); ((DecimalLiteralExpression)
		 * x).setValue(new BigDecimal(str.substring(startPos, this.pos)));
		 * 
		 * }
		 */
		else if (isVarChar(ch)) { // functions and variables and literals
			while (isVarChar(ch))
				nextChar();
			String str1 = str.substring(startPos, this.pos);
			if (str1.equals("true")) {
				x = expFactory.createTrueExpression();
			} else if (str1.equals("false")) {
				x = expFactory.createFalseExpression();
			}else if (str1.equals("else")) {
				x = expFactory.createElseExpression();
			} else if (eat('(')) {
				x = parseExpression();
				if (!eat(')')) {
					throw new GammaTransformationException("Missing ')' after argument to " + str1,statechart);
				} else {

				}
			} else if (eat('.')) {
				int midPos = this.pos;
				while (isVarChar(ch))
					nextChar();
				String str2 = str.substring(midPos, this.pos);
				BigDecimal decLiteral = null;
				try {
					decLiteral = new BigDecimal(str1 + "." + str2);
					var decLitExp = expFactory.createDecimalLiteralExpression();
					decLitExp.setValue(decLiteral);
					x = decLitExp;
				} catch (NumberFormatException e) {
				}
				if (decLiteral == null) {
					var ref = searchFlowProperty(str1, str2);
					x = ref;
				}
			} else if (eat(':')) {
				int midPos = this.pos;
				while (isVarChar(ch))
					nextChar();
				String str2 = str.substring(midPos, this.pos);

				var enumDecls = trace.getInterfacePackage().getTypeDeclarations().stream()
						.filter(decl -> decl.getType() instanceof EnumerableTypeDefinition)
						.filter(decl -> decl.getName().equals(TransformationUtils.getGammaName(str1) + "_Enum"))
						.toList();

				if (enumDecls.size() == 0) {
					throw new GammaTransformationException(
							"Parser Exception; Enum Type reference is not found '" + str1 + "' in '" + str + "'",statechart);
				}

				if (enumDecls.size() > 1) {
					throw new GammaTransformationException(
							"Parser Exception; Enum Type reference is ambigous '" + str1 + "' in '" + str + "'",statechart);
				}

				var enumLitExp = expFactory.createEnumerationLiteralExpression();
				var typeRef = expFactory.createTypeReference();
				var enumDecl = enumDecls.get(0);
				typeRef.setReference(enumDecl);
				enumLitExp.setTypeReference(typeRef);
				var enumLits = ((EnumerationTypeDefinition) enumDecl.getType()).getLiterals().stream()
						.filter(lit -> lit.getName().equals(str2)).toList();
				if (enumLits.size() == 0) {
					throw new GammaTransformationException(
							"Parser Exception; Enum Literal reference is not found '" + str2 + "' in '" + str + "'",statechart);
				}

				if (enumLits.size() > 1) {
					throw new GammaTransformationException(
							"Parser Exception; Enum Literal reference is ambigous '" + str2 + "' in '" + str + "'",statechart);
				}
				enumLitExp.setReference(enumLits.get(0));

				x = enumLitExp;

			} else {
				BigInteger intLiteral = null;
				try {
					intLiteral = new BigInteger(str1);
					var intLitExp = expFactory.createIntegerLiteralExpression();
					intLitExp.setValue(intLiteral);
					x = intLitExp;
				} catch (NumberFormatException e) {
				}
				if (intLiteral == null) {
					var ref = searchVariable(str1);
					x = ref;
				}
				// x = parseFactor();
			}
			/*
			 * if (func.equals("sqrt")) x = Math.sqrt(x); else if (func.equals("sin")) x =
			 * Math.sin(Math.toRadians(x)); else if (func.equals("cos")) x =
			 * Math.cos(Math.toRadians(x)); else if (func.equals("tan")) x =
			 * Math.tan(Math.toRadians(x)); else throw new
			 * GammaTransformationException("Unknown function: " + func);
			 * 
			 */
		} else {
			throw new GammaTransformationException("Parser Error; Unexpected character: " + (char) ch + " ASCII code :"
					+ ch + " in expression: " + str + " at " + pos,statechart);
		}

		// if (eat('^'))
		// x = Math.pow(x, parseFactor()); // exponentiation

		return x;
	}

}
