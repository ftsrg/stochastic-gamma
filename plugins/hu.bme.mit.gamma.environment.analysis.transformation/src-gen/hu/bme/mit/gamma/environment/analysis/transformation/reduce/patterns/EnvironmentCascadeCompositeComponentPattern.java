/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.analysis.transformation/src/hu/bme/mit/gamma/environment/analysis/transformation/reduce/patterns/Patterns.vql
 */
package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns;

import hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PVisibility;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuples;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         pattern EnvironmentCascadeCompositeComponentPattern  (component : EnvironmentCascadeCompositeComponent){
 *         	EnvironmentCascadeCompositeComponent(component);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class EnvironmentCascadeCompositeComponentPattern extends BaseGeneratedEMFQuerySpecification<EnvironmentCascadeCompositeComponentPattern.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentPattern pattern,
   * to be used in conjunction with {@link Matcher}.
   * 
   * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
   * Each instance is a (possibly partial) substitution of pattern parameters,
   * usable to represent a match of the pattern in the result of a query,
   * or to specify the bound (fixed) input parameters when issuing a query.
   * 
   * @see Matcher
   * 
   */
  public static abstract class Match extends BasePatternMatch {
    private EnvironmentCascadeCompositeComponent fComponent;

    private static List<String> parameterNames = makeImmutableList("component");

    private Match(final EnvironmentCascadeCompositeComponent pComponent) {
      this.fComponent = pComponent;
    }

    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "component": return this.fComponent;
          default: return null;
      }
    }

    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fComponent;
          default: return null;
      }
    }

    public EnvironmentCascadeCompositeComponent getComponent() {
      return this.fComponent;
    }

    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("component".equals(parameterName) ) {
          this.fComponent = (EnvironmentCascadeCompositeComponent) newValue;
          return true;
      }
      return false;
    }

    public void setComponent(final EnvironmentCascadeCompositeComponent pComponent) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fComponent = pComponent;
    }

    @Override
    public String patternName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentPattern";
    }

    @Override
    public List<String> parameterNames() {
      return EnvironmentCascadeCompositeComponentPattern.Match.parameterNames;
    }

    @Override
    public Object[] toArray() {
      return new Object[]{fComponent};
    }

    @Override
    public EnvironmentCascadeCompositeComponentPattern.Match toImmutable() {
      return isMutable() ? newMatch(fComponent) : this;
    }

    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"component\"=" + prettyPrintValue(fComponent));
      return result.toString();
    }

    @Override
    public int hashCode() {
      return Objects.hash(fComponent);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof EnvironmentCascadeCompositeComponentPattern.Match)) {
          EnvironmentCascadeCompositeComponentPattern.Match other = (EnvironmentCascadeCompositeComponentPattern.Match) obj;
          return Objects.equals(fComponent, other.fComponent);
      } else {
          // this should be infrequent
          if (!(obj instanceof IPatternMatch)) {
              return false;
          }
          IPatternMatch otherSig  = (IPatternMatch) obj;
          return Objects.equals(specification(), otherSig.specification()) && Arrays.deepEquals(toArray(), otherSig.toArray());
      }
    }

    @Override
    public EnvironmentCascadeCompositeComponentPattern specification() {
      return EnvironmentCascadeCompositeComponentPattern.instance();
    }

    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static EnvironmentCascadeCompositeComponentPattern.Match newEmptyMatch() {
      return new Mutable(null);
    }

    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static EnvironmentCascadeCompositeComponentPattern.Match newMutableMatch(final EnvironmentCascadeCompositeComponent pComponent) {
      return new Mutable(pComponent);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static EnvironmentCascadeCompositeComponentPattern.Match newMatch(final EnvironmentCascadeCompositeComponent pComponent) {
      return new Immutable(pComponent);
    }

    private static final class Mutable extends EnvironmentCascadeCompositeComponentPattern.Match {
      Mutable(final EnvironmentCascadeCompositeComponent pComponent) {
        super(pComponent);
      }

      @Override
      public boolean isMutable() {
        return true;
      }
    }

    private static final class Immutable extends EnvironmentCascadeCompositeComponentPattern.Match {
      Immutable(final EnvironmentCascadeCompositeComponent pComponent) {
        super(pComponent);
      }

      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }

  /**
   * Generated pattern matcher API of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentPattern pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern EnvironmentCascadeCompositeComponentPattern  (component : EnvironmentCascadeCompositeComponent){
   * 	EnvironmentCascadeCompositeComponent(component);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see EnvironmentCascadeCompositeComponentPattern
   * 
   */
  public static class Matcher extends BaseMatcher<EnvironmentCascadeCompositeComponentPattern.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static EnvironmentCascadeCompositeComponentPattern.Matcher on(final ViatraQueryEngine engine) {
      // check if matcher already exists
      Matcher matcher = engine.getExistingMatcher(querySpecification());
      if (matcher == null) {
          matcher = (Matcher)engine.getMatcher(querySpecification());
      }
      return matcher;
    }

    /**
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * @return an initialized matcher
     * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
     * 
     */
    public static EnvironmentCascadeCompositeComponentPattern.Matcher create() {
      return new Matcher();
    }

    private static final int POSITION_COMPONENT = 0;

    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(EnvironmentCascadeCompositeComponentPattern.Matcher.class);

    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    private Matcher() {
      super(querySpecification());
    }

    /**
     * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<EnvironmentCascadeCompositeComponentPattern.Match> getAllMatches(final EnvironmentCascadeCompositeComponent pComponent) {
      return rawStreamAllMatches(new Object[]{pComponent}).collect(Collectors.toSet());
    }

    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<EnvironmentCascadeCompositeComponentPattern.Match> streamAllMatches(final EnvironmentCascadeCompositeComponent pComponent) {
      return rawStreamAllMatches(new Object[]{pComponent});
    }

    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<EnvironmentCascadeCompositeComponentPattern.Match> getOneArbitraryMatch(final EnvironmentCascadeCompositeComponent pComponent) {
      return rawGetOneArbitraryMatch(new Object[]{pComponent});
    }

    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final EnvironmentCascadeCompositeComponent pComponent) {
      return rawHasMatch(new Object[]{pComponent});
    }

    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final EnvironmentCascadeCompositeComponent pComponent) {
      return rawCountMatches(new Object[]{pComponent});
    }

    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final EnvironmentCascadeCompositeComponent pComponent, final Consumer<? super EnvironmentCascadeCompositeComponentPattern.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pComponent}, processor);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public EnvironmentCascadeCompositeComponentPattern.Match newMatch(final EnvironmentCascadeCompositeComponent pComponent) {
      return EnvironmentCascadeCompositeComponentPattern.Match.newMatch(pComponent);
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<EnvironmentCascadeCompositeComponent> rawStreamAllValuesOfcomponent(final Object[] parameters) {
      return rawStreamAllValues(POSITION_COMPONENT, parameters).map(EnvironmentCascadeCompositeComponent.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentCascadeCompositeComponent> getAllValuesOfcomponent() {
      return rawStreamAllValuesOfcomponent(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<EnvironmentCascadeCompositeComponent> streamAllValuesOfcomponent() {
      return rawStreamAllValuesOfcomponent(emptyArray());
    }

    @Override
    protected EnvironmentCascadeCompositeComponentPattern.Match tupleToMatch(final Tuple t) {
      try {
          return EnvironmentCascadeCompositeComponentPattern.Match.newMatch((EnvironmentCascadeCompositeComponent) t.get(POSITION_COMPONENT));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }

    @Override
    protected EnvironmentCascadeCompositeComponentPattern.Match arrayToMatch(final Object[] match) {
      try {
          return EnvironmentCascadeCompositeComponentPattern.Match.newMatch((EnvironmentCascadeCompositeComponent) match[POSITION_COMPONENT]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }

    @Override
    protected EnvironmentCascadeCompositeComponentPattern.Match arrayToMatchMutable(final Object[] match) {
      try {
          return EnvironmentCascadeCompositeComponentPattern.Match.newMutableMatch((EnvironmentCascadeCompositeComponent) match[POSITION_COMPONENT]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }

    /**
     * @return the singleton instance of the query specification of this pattern
     * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
     * 
     */
    public static IQuerySpecification<EnvironmentCascadeCompositeComponentPattern.Matcher> querySpecification() {
      return EnvironmentCascadeCompositeComponentPattern.instance();
    }
  }

  private EnvironmentCascadeCompositeComponentPattern() {
    super(GeneratedPQuery.INSTANCE);
  }

  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static EnvironmentCascadeCompositeComponentPattern instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }

  @Override
  protected EnvironmentCascadeCompositeComponentPattern.Matcher instantiate(final ViatraQueryEngine engine) {
    return EnvironmentCascadeCompositeComponentPattern.Matcher.on(engine);
  }

  @Override
  public EnvironmentCascadeCompositeComponentPattern.Matcher instantiate() {
    return EnvironmentCascadeCompositeComponentPattern.Matcher.create();
  }

  @Override
  public EnvironmentCascadeCompositeComponentPattern.Match newEmptyMatch() {
    return EnvironmentCascadeCompositeComponentPattern.Match.newEmptyMatch();
  }

  @Override
  public EnvironmentCascadeCompositeComponentPattern.Match newMatch(final Object... parameters) {
    return EnvironmentCascadeCompositeComponentPattern.Match.newMatch((hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent) parameters[0]);
  }

  /**
   * Inner class allowing the singleton instance of {@link EnvironmentCascadeCompositeComponentPattern} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link EnvironmentCascadeCompositeComponentPattern#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final EnvironmentCascadeCompositeComponentPattern INSTANCE = new EnvironmentCascadeCompositeComponentPattern();

    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private static final Object STATIC_INITIALIZER = ensureInitialized();

    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternal();
      return null;
    }
  }

  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private static final EnvironmentCascadeCompositeComponentPattern.GeneratedPQuery INSTANCE = new GeneratedPQuery();

    private final PParameter parameter_component = new PParameter("component", "hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("www.mit.bme.hu/gamma/environment/Model", "EnvironmentCascadeCompositeComponent")), PParameterDirection.INOUT);

    private final List<PParameter> parameters = Arrays.asList(parameter_component);

    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }

    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentCascadeCompositeComponentPattern";
    }

    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("component");
    }

    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }

    @Override
    public Set<PBody> doGetContainedBodies() {
      setEvaluationHints(new QueryEvaluationHint(null, QueryEvaluationHint.BackendRequirement.UNSPECIFIED));
      Set<PBody> bodies = new LinkedHashSet<>();
      {
          PBody body = new PBody(this);
          PVariable var_component = body.getOrCreateVariableByName("component");
          new TypeConstraint(body, Tuples.flatTupleOf(var_component), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentCascadeCompositeComponent")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_component, parameter_component)
          ));
          // 	EnvironmentCascadeCompositeComponent(component)
          new TypeConstraint(body, Tuples.flatTupleOf(var_component), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentCascadeCompositeComponent")));
          bodies.add(body);
      }
      return bodies;
    }
  }
}
