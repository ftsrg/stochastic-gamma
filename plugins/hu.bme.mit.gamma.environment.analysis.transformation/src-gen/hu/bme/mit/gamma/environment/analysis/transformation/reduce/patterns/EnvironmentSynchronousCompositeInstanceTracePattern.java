/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.analysis.transformation/src/hu/bme/mit/gamma/environment/analysis/transformation/reduce/patterns/TracePatterns.vql
 */
package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns;

import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance;
import hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance;
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
 *         pattern EnvironmentSynchronousCompositeInstanceTracePattern (source : EnvironmentSynchronousCompositeComponentInstance, target : SynchronousComponentInstance){
 *         	SynchronousCompositeInstanceTrace.source(trace, source);
 *         	SynchronousCompositeInstanceTrace.target(trace, target);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class EnvironmentSynchronousCompositeInstanceTracePattern extends BaseGeneratedEMFQuerySpecification<EnvironmentSynchronousCompositeInstanceTracePattern.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeInstanceTracePattern pattern,
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
    private EnvironmentSynchronousCompositeComponentInstance fSource;

    private SynchronousComponentInstance fTarget;

    private static List<String> parameterNames = makeImmutableList("source", "target");

    private Match(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      this.fSource = pSource;
      this.fTarget = pTarget;
    }

    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "source": return this.fSource;
          case "target": return this.fTarget;
          default: return null;
      }
    }

    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fSource;
          case 1: return this.fTarget;
          default: return null;
      }
    }

    public EnvironmentSynchronousCompositeComponentInstance getSource() {
      return this.fSource;
    }

    public SynchronousComponentInstance getTarget() {
      return this.fTarget;
    }

    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("source".equals(parameterName) ) {
          this.fSource = (EnvironmentSynchronousCompositeComponentInstance) newValue;
          return true;
      }
      if ("target".equals(parameterName) ) {
          this.fTarget = (SynchronousComponentInstance) newValue;
          return true;
      }
      return false;
    }

    public void setSource(final EnvironmentSynchronousCompositeComponentInstance pSource) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSource = pSource;
    }

    public void setTarget(final SynchronousComponentInstance pTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTarget = pTarget;
    }

    @Override
    public String patternName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeInstanceTracePattern";
    }

    @Override
    public List<String> parameterNames() {
      return EnvironmentSynchronousCompositeInstanceTracePattern.Match.parameterNames;
    }

    @Override
    public Object[] toArray() {
      return new Object[]{fSource, fTarget};
    }

    @Override
    public EnvironmentSynchronousCompositeInstanceTracePattern.Match toImmutable() {
      return isMutable() ? newMatch(fSource, fTarget) : this;
    }

    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"source\"=" + prettyPrintValue(fSource) + ", ");
      result.append("\"target\"=" + prettyPrintValue(fTarget));
      return result.toString();
    }

    @Override
    public int hashCode() {
      return Objects.hash(fSource, fTarget);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof EnvironmentSynchronousCompositeInstanceTracePattern.Match)) {
          EnvironmentSynchronousCompositeInstanceTracePattern.Match other = (EnvironmentSynchronousCompositeInstanceTracePattern.Match) obj;
          return Objects.equals(fSource, other.fSource) && Objects.equals(fTarget, other.fTarget);
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
    public EnvironmentSynchronousCompositeInstanceTracePattern specification() {
      return EnvironmentSynchronousCompositeInstanceTracePattern.instance();
    }

    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static EnvironmentSynchronousCompositeInstanceTracePattern.Match newEmptyMatch() {
      return new Mutable(null, null);
    }

    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static EnvironmentSynchronousCompositeInstanceTracePattern.Match newMutableMatch(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return new Mutable(pSource, pTarget);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static EnvironmentSynchronousCompositeInstanceTracePattern.Match newMatch(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return new Immutable(pSource, pTarget);
    }

    private static final class Mutable extends EnvironmentSynchronousCompositeInstanceTracePattern.Match {
      Mutable(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
        super(pSource, pTarget);
      }

      @Override
      public boolean isMutable() {
        return true;
      }
    }

    private static final class Immutable extends EnvironmentSynchronousCompositeInstanceTracePattern.Match {
      Immutable(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
        super(pSource, pTarget);
      }

      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }

  /**
   * Generated pattern matcher API of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeInstanceTracePattern pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern EnvironmentSynchronousCompositeInstanceTracePattern (source : EnvironmentSynchronousCompositeComponentInstance, target : SynchronousComponentInstance){
   * 	SynchronousCompositeInstanceTrace.source(trace, source);
   * 	SynchronousCompositeInstanceTrace.target(trace, target);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see EnvironmentSynchronousCompositeInstanceTracePattern
   * 
   */
  public static class Matcher extends BaseMatcher<EnvironmentSynchronousCompositeInstanceTracePattern.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static EnvironmentSynchronousCompositeInstanceTracePattern.Matcher on(final ViatraQueryEngine engine) {
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
    public static EnvironmentSynchronousCompositeInstanceTracePattern.Matcher create() {
      return new Matcher();
    }

    private static final int POSITION_SOURCE = 0;

    private static final int POSITION_TARGET = 1;

    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(EnvironmentSynchronousCompositeInstanceTracePattern.Matcher.class);

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
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<EnvironmentSynchronousCompositeInstanceTracePattern.Match> getAllMatches(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawStreamAllMatches(new Object[]{pSource, pTarget}).collect(Collectors.toSet());
    }

    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<EnvironmentSynchronousCompositeInstanceTracePattern.Match> streamAllMatches(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawStreamAllMatches(new Object[]{pSource, pTarget});
    }

    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<EnvironmentSynchronousCompositeInstanceTracePattern.Match> getOneArbitraryMatch(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawGetOneArbitraryMatch(new Object[]{pSource, pTarget});
    }

    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawHasMatch(new Object[]{pSource, pTarget});
    }

    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawCountMatches(new Object[]{pSource, pTarget});
    }

    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget, final Consumer<? super EnvironmentSynchronousCompositeInstanceTracePattern.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pSource, pTarget}, processor);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public EnvironmentSynchronousCompositeInstanceTracePattern.Match newMatch(final EnvironmentSynchronousCompositeComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return EnvironmentSynchronousCompositeInstanceTracePattern.Match.newMatch(pSource, pTarget);
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<EnvironmentSynchronousCompositeComponentInstance> rawStreamAllValuesOfsource(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SOURCE, parameters).map(EnvironmentSynchronousCompositeComponentInstance.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentSynchronousCompositeComponentInstance> getAllValuesOfsource() {
      return rawStreamAllValuesOfsource(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<EnvironmentSynchronousCompositeComponentInstance> streamAllValuesOfsource() {
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
    public Stream<EnvironmentSynchronousCompositeComponentInstance> streamAllValuesOfsource(final EnvironmentSynchronousCompositeInstanceTracePattern.Match partialMatch) {
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
    public Stream<EnvironmentSynchronousCompositeComponentInstance> streamAllValuesOfsource(final SynchronousComponentInstance pTarget) {
      return rawStreamAllValuesOfsource(new Object[]{null, pTarget});
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentSynchronousCompositeComponentInstance> getAllValuesOfsource(final EnvironmentSynchronousCompositeInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOfsource(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentSynchronousCompositeComponentInstance> getAllValuesOfsource(final SynchronousComponentInstance pTarget) {
      return rawStreamAllValuesOfsource(new Object[]{null, pTarget}).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<SynchronousComponentInstance> rawStreamAllValuesOftarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TARGET, parameters).map(SynchronousComponentInstance.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<SynchronousComponentInstance> getAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<SynchronousComponentInstance> streamAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray());
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<SynchronousComponentInstance> streamAllValuesOftarget(final EnvironmentSynchronousCompositeInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray());
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<SynchronousComponentInstance> streamAllValuesOftarget(final EnvironmentSynchronousCompositeComponentInstance pSource) {
      return rawStreamAllValuesOftarget(new Object[]{pSource, null});
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<SynchronousComponentInstance> getAllValuesOftarget(final EnvironmentSynchronousCompositeInstanceTracePattern.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<SynchronousComponentInstance> getAllValuesOftarget(final EnvironmentSynchronousCompositeComponentInstance pSource) {
      return rawStreamAllValuesOftarget(new Object[]{pSource, null}).collect(Collectors.toSet());
    }

    @Override
    protected EnvironmentSynchronousCompositeInstanceTracePattern.Match tupleToMatch(final Tuple t) {
      try {
          return EnvironmentSynchronousCompositeInstanceTracePattern.Match.newMatch((EnvironmentSynchronousCompositeComponentInstance) t.get(POSITION_SOURCE), (SynchronousComponentInstance) t.get(POSITION_TARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }

    @Override
    protected EnvironmentSynchronousCompositeInstanceTracePattern.Match arrayToMatch(final Object[] match) {
      try {
          return EnvironmentSynchronousCompositeInstanceTracePattern.Match.newMatch((EnvironmentSynchronousCompositeComponentInstance) match[POSITION_SOURCE], (SynchronousComponentInstance) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }

    @Override
    protected EnvironmentSynchronousCompositeInstanceTracePattern.Match arrayToMatchMutable(final Object[] match) {
      try {
          return EnvironmentSynchronousCompositeInstanceTracePattern.Match.newMutableMatch((EnvironmentSynchronousCompositeComponentInstance) match[POSITION_SOURCE], (SynchronousComponentInstance) match[POSITION_TARGET]);
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
    public static IQuerySpecification<EnvironmentSynchronousCompositeInstanceTracePattern.Matcher> querySpecification() {
      return EnvironmentSynchronousCompositeInstanceTracePattern.instance();
    }
  }

  private EnvironmentSynchronousCompositeInstanceTracePattern() {
    super(GeneratedPQuery.INSTANCE);
  }

  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static EnvironmentSynchronousCompositeInstanceTracePattern instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }

  @Override
  protected EnvironmentSynchronousCompositeInstanceTracePattern.Matcher instantiate(final ViatraQueryEngine engine) {
    return EnvironmentSynchronousCompositeInstanceTracePattern.Matcher.on(engine);
  }

  @Override
  public EnvironmentSynchronousCompositeInstanceTracePattern.Matcher instantiate() {
    return EnvironmentSynchronousCompositeInstanceTracePattern.Matcher.create();
  }

  @Override
  public EnvironmentSynchronousCompositeInstanceTracePattern.Match newEmptyMatch() {
    return EnvironmentSynchronousCompositeInstanceTracePattern.Match.newEmptyMatch();
  }

  @Override
  public EnvironmentSynchronousCompositeInstanceTracePattern.Match newMatch(final Object... parameters) {
    return EnvironmentSynchronousCompositeInstanceTracePattern.Match.newMatch((hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance) parameters[0], (hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance) parameters[1]);
  }

  /**
   * Inner class allowing the singleton instance of {@link EnvironmentSynchronousCompositeInstanceTracePattern} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link EnvironmentSynchronousCompositeInstanceTracePattern#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final EnvironmentSynchronousCompositeInstanceTracePattern INSTANCE = new EnvironmentSynchronousCompositeInstanceTracePattern();

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
    private static final EnvironmentSynchronousCompositeInstanceTracePattern.GeneratedPQuery INSTANCE = new GeneratedPQuery();

    private final PParameter parameter_source = new PParameter("source", "hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponentInstance", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("www.mit.bme.hu/gamma/environment/Model", "EnvironmentSynchronousCompositeComponentInstance")), PParameterDirection.INOUT);

    private final PParameter parameter_target = new PParameter("target", "hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SynchronousComponentInstance")), PParameterDirection.INOUT);

    private final List<PParameter> parameters = Arrays.asList(parameter_source, parameter_target);

    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }

    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.EnvironmentSynchronousCompositeInstanceTracePattern";
    }

    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("source","target");
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
          PVariable var_target = body.getOrCreateVariableByName("target");
          PVariable var_trace = body.getOrCreateVariableByName("trace");
          new TypeConstraint(body, Tuples.flatTupleOf(var_source), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentSynchronousCompositeComponentInstance")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SynchronousComponentInstance")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_source, parameter_source),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	SynchronousCompositeInstanceTrace.source(trace, source)
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "SynchronousCompositeInstanceTrace")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "SynchronousCompositeInstanceTrace", "source")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentSynchronousCompositeComponentInstance")));
          new Equality(body, var__virtual_0_, var_source);
          // 	SynchronousCompositeInstanceTrace.target(trace, target)
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "SynchronousCompositeInstanceTrace")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_trace, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/environment/transformation/Traceability", "SynchronousCompositeInstanceTrace", "target")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SynchronousComponentInstance")));
          new Equality(body, var__virtual_1_, var_target);
          bodies.add(body);
      }
      return bodies;
    }
  }
}
