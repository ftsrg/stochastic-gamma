/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.analysis.transformation/src/hu/bme/mit/gamma/environment/analysis/transformation/reduce/patterns/TracePatterns.vql
 */
package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.statechart.composite.ComponentInstance;
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition;
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
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
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
 *         pattern ElementaryEnvironmentComponentInstanceTracePattern (source : ElementaryEnvironmentComponentInstance, typeTarget : StatechartDefinition, instanceTarget : ComponentInstance){
 *         	ElementaryTrace.source(trace,source);
 *         	ElementaryTrace.typeTarget(trace,typeTarget);
 *         	ElementaryTrace.instanceTarget(trace,instanceTarget);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class ElementaryEnvironmentComponentInstanceTracePattern extends BaseGeneratedEMFQuerySpecification<ElementaryEnvironmentComponentInstanceTracePattern.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.ElementaryEnvironmentComponentInstanceTracePattern pattern,
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
    private ElementaryEnvironmentComponentInstance fSource;

    private StatechartDefinition fTypeTarget;

    private ComponentInstance fInstanceTarget;

    private static List<String> parameterNames = makeImmutableList("source", "typeTarget", "instanceTarget");

    private Match(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      this.fSource = pSource;
      this.fTypeTarget = pTypeTarget;
      this.fInstanceTarget = pInstanceTarget;
    }

    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "source": return this.fSource;
          case "typeTarget": return this.fTypeTarget;
          case "instanceTarget": return this.fInstanceTarget;
          default: return null;
      }
    }

    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fSource;
          case 1: return this.fTypeTarget;
          case 2: return this.fInstanceTarget;
          default: return null;
      }
    }

    public ElementaryEnvironmentComponentInstance getSource() {
      return this.fSource;
    }

    public StatechartDefinition getTypeTarget() {
      return this.fTypeTarget;
    }

    public ComponentInstance getInstanceTarget() {
      return this.fInstanceTarget;
    }

    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("source".equals(parameterName) ) {
          this.fSource = (ElementaryEnvironmentComponentInstance) newValue;
          return true;
      }
      if ("typeTarget".equals(parameterName) ) {
          this.fTypeTarget = (StatechartDefinition) newValue;
          return true;
      }
      if ("instanceTarget".equals(parameterName) ) {
          this.fInstanceTarget = (ComponentInstance) newValue;
          return true;
      }
      return false;
    }

    public void setSource(final ElementaryEnvironmentComponentInstance pSource) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSource = pSource;
    }

    public void setTypeTarget(final StatechartDefinition pTypeTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTypeTarget = pTypeTarget;
    }

    public void setInstanceTarget(final ComponentInstance pInstanceTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fInstanceTarget = pInstanceTarget;
    }

    @Override
    public String patternName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.ElementaryEnvironmentComponentInstanceTracePattern";
    }

    @Override
    public List<String> parameterNames() {
      return ElementaryEnvironmentComponentInstanceTracePattern.Match.parameterNames;
    }

    @Override
    public Object[] toArray() {
      return new Object[]{fSource, fTypeTarget, fInstanceTarget};
    }

    @Override
    public ElementaryEnvironmentComponentInstanceTracePattern.Match toImmutable() {
      return isMutable() ? newMatch(fSource, fTypeTarget, fInstanceTarget) : this;
    }

    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"source\"=" + prettyPrintValue(fSource) + ", ");
      result.append("\"typeTarget\"=" + prettyPrintValue(fTypeTarget) + ", ");
      result.append("\"instanceTarget\"=" + prettyPrintValue(fInstanceTarget));
      return result.toString();
    }

    @Override
    public int hashCode() {
      return Objects.hash(fSource, fTypeTarget, fInstanceTarget);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof ElementaryEnvironmentComponentInstanceTracePattern.Match)) {
          ElementaryEnvironmentComponentInstanceTracePattern.Match other = (ElementaryEnvironmentComponentInstanceTracePattern.Match) obj;
          return Objects.equals(fSource, other.fSource) && Objects.equals(fTypeTarget, other.fTypeTarget) && Objects.equals(fInstanceTarget, other.fInstanceTarget);
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
    public ElementaryEnvironmentComponentInstanceTracePattern specification() {
      return ElementaryEnvironmentComponentInstanceTracePattern.instance();
    }

    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static ElementaryEnvironmentComponentInstanceTracePattern.Match newEmptyMatch() {
      return new Mutable(null, null, null);
    }

    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static ElementaryEnvironmentComponentInstanceTracePattern.Match newMutableMatch(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return new Mutable(pSource, pTypeTarget, pInstanceTarget);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static ElementaryEnvironmentComponentInstanceTracePattern.Match newMatch(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return new Immutable(pSource, pTypeTarget, pInstanceTarget);
    }

    private static final class Mutable extends ElementaryEnvironmentComponentInstanceTracePattern.Match {
      Mutable(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
        super(pSource, pTypeTarget, pInstanceTarget);
      }

      @Override
      public boolean isMutable() {
        return true;
      }
    }

    private static final class Immutable extends ElementaryEnvironmentComponentInstanceTracePattern.Match {
      Immutable(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
        super(pSource, pTypeTarget, pInstanceTarget);
      }

      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }

  /**
   * Generated pattern matcher API of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.ElementaryEnvironmentComponentInstanceTracePattern pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern ElementaryEnvironmentComponentInstanceTracePattern (source : ElementaryEnvironmentComponentInstance, typeTarget : StatechartDefinition, instanceTarget : ComponentInstance){
   * 	ElementaryTrace.source(trace,source);
   * 	ElementaryTrace.typeTarget(trace,typeTarget);
   * 	ElementaryTrace.instanceTarget(trace,instanceTarget);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see ElementaryEnvironmentComponentInstanceTracePattern
   * 
   */
  public static class Matcher extends BaseMatcher<ElementaryEnvironmentComponentInstanceTracePattern.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static ElementaryEnvironmentComponentInstanceTracePattern.Matcher on(final ViatraQueryEngine engine) {
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
    public static ElementaryEnvironmentComponentInstanceTracePattern.Matcher create() {
      return new Matcher();
    }

    private static final int POSITION_SOURCE = 0;

    private static final int POSITION_TYPETARGET = 1;

    private static final int POSITION_INSTANCETARGET = 2;

    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ElementaryEnvironmentComponentInstanceTracePattern.Matcher.class);

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
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<ElementaryEnvironmentComponentInstanceTracePattern.Match> getAllMatches(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return rawStreamAllMatches(new Object[]{pSource, pTypeTarget, pInstanceTarget}).collect(Collectors.toSet());
    }

    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<ElementaryEnvironmentComponentInstanceTracePattern.Match> streamAllMatches(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return rawStreamAllMatches(new Object[]{pSource, pTypeTarget, pInstanceTarget});
    }

    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<ElementaryEnvironmentComponentInstanceTracePattern.Match> getOneArbitraryMatch(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return rawGetOneArbitraryMatch(new Object[]{pSource, pTypeTarget, pInstanceTarget});
    }

    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return rawHasMatch(new Object[]{pSource, pTypeTarget, pInstanceTarget});
    }

    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return rawCountMatches(new Object[]{pSource, pTypeTarget, pInstanceTarget});
    }

    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget, final Consumer<? super ElementaryEnvironmentComponentInstanceTracePattern.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pSource, pTypeTarget, pInstanceTarget}, processor);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTypeTarget the fixed value of pattern parameter typeTarget, or null if not bound.
     * @param pInstanceTarget the fixed value of pattern parameter instanceTarget, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public ElementaryEnvironmentComponentInstanceTracePattern.Match newMatch(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return ElementaryEnvironmentComponentInstanceTracePattern.Match.newMatch(pSource, pTypeTarget, pInstanceTarget);
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<ElementaryEnvironmentComponentInstance> rawStreamAllValuesOfsource(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SOURCE, parameters).map(ElementaryEnvironmentComponentInstance.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfsource() {
      return rawStreamAllValuesOfsource(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfsource() {
      return rawStreamAllValuesOfsource(emptyArray());
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfsource(final ElementaryEnvironmentComponentInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOfsource(partialMatch.toArray());
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfsource(final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return rawStreamAllValuesOfsource(new Object[]{null, pTypeTarget, pInstanceTarget});
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfsource(final ElementaryEnvironmentComponentInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOfsource(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfsource(final StatechartDefinition pTypeTarget, final ComponentInstance pInstanceTarget) {
      return rawStreamAllValuesOfsource(new Object[]{null, pTypeTarget, pInstanceTarget}).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for typeTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<StatechartDefinition> rawStreamAllValuesOftypeTarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TYPETARGET, parameters).map(StatechartDefinition.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for typeTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StatechartDefinition> getAllValuesOftypeTarget() {
      return rawStreamAllValuesOftypeTarget(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for typeTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<StatechartDefinition> streamAllValuesOftypeTarget() {
      return rawStreamAllValuesOftypeTarget(emptyArray());
    }

    /**
     * Retrieve the set of values that occur in matches for typeTarget.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<StatechartDefinition> streamAllValuesOftypeTarget(final ElementaryEnvironmentComponentInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOftypeTarget(partialMatch.toArray());
    }

    /**
     * Retrieve the set of values that occur in matches for typeTarget.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<StatechartDefinition> streamAllValuesOftypeTarget(final ElementaryEnvironmentComponentInstance pSource, final ComponentInstance pInstanceTarget) {
      return rawStreamAllValuesOftypeTarget(new Object[]{pSource, null, pInstanceTarget});
    }

    /**
     * Retrieve the set of values that occur in matches for typeTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StatechartDefinition> getAllValuesOftypeTarget(final ElementaryEnvironmentComponentInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOftypeTarget(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for typeTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<StatechartDefinition> getAllValuesOftypeTarget(final ElementaryEnvironmentComponentInstance pSource, final ComponentInstance pInstanceTarget) {
      return rawStreamAllValuesOftypeTarget(new Object[]{pSource, null, pInstanceTarget}).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for instanceTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<ComponentInstance> rawStreamAllValuesOfinstanceTarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_INSTANCETARGET, parameters).map(ComponentInstance.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for instanceTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ComponentInstance> getAllValuesOfinstanceTarget() {
      return rawStreamAllValuesOfinstanceTarget(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for instanceTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<ComponentInstance> streamAllValuesOfinstanceTarget() {
      return rawStreamAllValuesOfinstanceTarget(emptyArray());
    }

    /**
     * Retrieve the set of values that occur in matches for instanceTarget.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<ComponentInstance> streamAllValuesOfinstanceTarget(final ElementaryEnvironmentComponentInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOfinstanceTarget(partialMatch.toArray());
    }

    /**
     * Retrieve the set of values that occur in matches for instanceTarget.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<ComponentInstance> streamAllValuesOfinstanceTarget(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget) {
      return rawStreamAllValuesOfinstanceTarget(new Object[]{pSource, pTypeTarget, null});
    }

    /**
     * Retrieve the set of values that occur in matches for instanceTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ComponentInstance> getAllValuesOfinstanceTarget(final ElementaryEnvironmentComponentInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOfinstanceTarget(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for instanceTarget.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ComponentInstance> getAllValuesOfinstanceTarget(final ElementaryEnvironmentComponentInstance pSource, final StatechartDefinition pTypeTarget) {
      return rawStreamAllValuesOfinstanceTarget(new Object[]{pSource, pTypeTarget, null}).collect(Collectors.toSet());
    }

    @Override
    protected ElementaryEnvironmentComponentInstanceTracePattern.Match tupleToMatch(final Tuple t) {
      try {
          return ElementaryEnvironmentComponentInstanceTracePattern.Match.newMatch((ElementaryEnvironmentComponentInstance) t.get(POSITION_SOURCE), (StatechartDefinition) t.get(POSITION_TYPETARGET), (ComponentInstance) t.get(POSITION_INSTANCETARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }

    @Override
    protected ElementaryEnvironmentComponentInstanceTracePattern.Match arrayToMatch(final Object[] match) {
      try {
          return ElementaryEnvironmentComponentInstanceTracePattern.Match.newMatch((ElementaryEnvironmentComponentInstance) match[POSITION_SOURCE], (StatechartDefinition) match[POSITION_TYPETARGET], (ComponentInstance) match[POSITION_INSTANCETARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }

    @Override
    protected ElementaryEnvironmentComponentInstanceTracePattern.Match arrayToMatchMutable(final Object[] match) {
      try {
          return ElementaryEnvironmentComponentInstanceTracePattern.Match.newMutableMatch((ElementaryEnvironmentComponentInstance) match[POSITION_SOURCE], (StatechartDefinition) match[POSITION_TYPETARGET], (ComponentInstance) match[POSITION_INSTANCETARGET]);
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
    public static IQuerySpecification<ElementaryEnvironmentComponentInstanceTracePattern.Matcher> querySpecification() {
      return ElementaryEnvironmentComponentInstanceTracePattern.instance();
    }
  }

  private ElementaryEnvironmentComponentInstanceTracePattern() {
    super(GeneratedPQuery.INSTANCE);
  }

  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static ElementaryEnvironmentComponentInstanceTracePattern instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }

  @Override
  protected ElementaryEnvironmentComponentInstanceTracePattern.Matcher instantiate(final ViatraQueryEngine engine) {
    return ElementaryEnvironmentComponentInstanceTracePattern.Matcher.on(engine);
  }

  @Override
  public ElementaryEnvironmentComponentInstanceTracePattern.Matcher instantiate() {
    return ElementaryEnvironmentComponentInstanceTracePattern.Matcher.create();
  }

  @Override
  public ElementaryEnvironmentComponentInstanceTracePattern.Match newEmptyMatch() {
    return ElementaryEnvironmentComponentInstanceTracePattern.Match.newEmptyMatch();
  }

  @Override
  public ElementaryEnvironmentComponentInstanceTracePattern.Match newMatch(final Object... parameters) {
    return ElementaryEnvironmentComponentInstanceTracePattern.Match.newMatch((hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance) parameters[0], (hu.bme.mit.gamma.statechart.statechart.StatechartDefinition) parameters[1], (hu.bme.mit.gamma.statechart.composite.ComponentInstance) parameters[2]);
  }

  /**
   * Inner class allowing the singleton instance of {@link ElementaryEnvironmentComponentInstanceTracePattern} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link ElementaryEnvironmentComponentInstanceTracePattern#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final ElementaryEnvironmentComponentInstanceTracePattern INSTANCE = new ElementaryEnvironmentComponentInstanceTracePattern();

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
    private static final ElementaryEnvironmentComponentInstanceTracePattern.GeneratedPQuery INSTANCE = new GeneratedPQuery();

    private final PParameter parameter_source = new PParameter("source", "hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")), PParameterDirection.INOUT);

    private final PParameter parameter_typeTarget = new PParameter("typeTarget", "hu.bme.mit.gamma.statechart.statechart.StatechartDefinition", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StatechartDefinition")), PParameterDirection.INOUT);

    private final PParameter parameter_instanceTarget = new PParameter("instanceTarget", "hu.bme.mit.gamma.statechart.composite.ComponentInstance", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "ComponentInstance")), PParameterDirection.INOUT);

    private final List<PParameter> parameters = Arrays.asList(parameter_source, parameter_typeTarget, parameter_instanceTarget);

    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }

    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.ElementaryEnvironmentComponentInstanceTracePattern";
    }

    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("source","typeTarget","instanceTarget");
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
          PVariable var_source = body.getOrCreateVariableByName("source");
          PVariable var_typeTarget = body.getOrCreateVariableByName("typeTarget");
          PVariable var_instanceTarget = body.getOrCreateVariableByName("instanceTarget");
          PVariable var_trace = body.getOrCreateVariableByName("trace");
          new TypeConstraint(body, Tuples.flatTupleOf(var_source), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_typeTarget), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StatechartDefinition")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_instanceTarget), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "ComponentInstance")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_source, parameter_source),
             new ExportedParameter(body, var_typeTarget, parameter_typeTarget),
             new ExportedParameter(body, var_instanceTarget, parameter_instanceTarget)
          ));
          // 	ElementaryTrace.source(trace,source)
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "ElementaryTrace")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "ElementaryTrace", "source")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")));
          new Equality(body, var__virtual_0_, var_source);
          // 	ElementaryTrace.typeTarget(trace,typeTarget)
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "ElementaryTrace")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "ElementaryTrace", "typeTarget")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Statechart", "StatechartDefinition")));
          new Equality(body, var__virtual_1_, var_typeTarget);
          // 	ElementaryTrace.instanceTarget(trace,instanceTarget)
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "ElementaryTrace")));
          PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "ElementaryTrace", "instanceTarget")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "ComponentInstance")));
          new Equality(body, var__virtual_2_, var_instanceTarget);
          bodies.add(body);
      }
      return bodies;
    }
  }
}
