/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.analysis.transformation/src/hu/bme/mit/gamma/environment/analysis/transformation/reduce/patterns/Patterns.vql
 */
package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent;
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
 *         pattern SynchronousElementaryEnvironmentComponentInstancePattern  (instance : ElementaryEnvironmentComponentInstance, component : EnvironmentSynchronousCompositeComponent){
 *         	ElementaryEnvironmentComponentInstance (instance);
 *         	EnvironmentSynchronousCompositeComponent(component);
 *         	EnvironmentSynchronousCompositeComponent.environmentComponents(component,instance);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class SynchronousElementaryEnvironmentComponentInstancePattern extends BaseGeneratedEMFQuerySpecification<SynchronousElementaryEnvironmentComponentInstancePattern.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.SynchronousElementaryEnvironmentComponentInstancePattern pattern,
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
    private ElementaryEnvironmentComponentInstance fInstance;

    private EnvironmentSynchronousCompositeComponent fComponent;

    private static List<String> parameterNames = makeImmutableList("instance", "component");

    private Match(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      this.fInstance = pInstance;
      this.fComponent = pComponent;
    }

    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "instance": return this.fInstance;
          case "component": return this.fComponent;
          default: return null;
      }
    }

    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fInstance;
          case 1: return this.fComponent;
          default: return null;
      }
    }

    public ElementaryEnvironmentComponentInstance getInstance() {
      return this.fInstance;
    }

    public EnvironmentSynchronousCompositeComponent getComponent() {
      return this.fComponent;
    }

    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("instance".equals(parameterName) ) {
          this.fInstance = (ElementaryEnvironmentComponentInstance) newValue;
          return true;
      }
      if ("component".equals(parameterName) ) {
          this.fComponent = (EnvironmentSynchronousCompositeComponent) newValue;
          return true;
      }
      return false;
    }

    public void setInstance(final ElementaryEnvironmentComponentInstance pInstance) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fInstance = pInstance;
    }

    public void setComponent(final EnvironmentSynchronousCompositeComponent pComponent) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fComponent = pComponent;
    }

    @Override
    public String patternName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.SynchronousElementaryEnvironmentComponentInstancePattern";
    }

    @Override
    public List<String> parameterNames() {
      return SynchronousElementaryEnvironmentComponentInstancePattern.Match.parameterNames;
    }

    @Override
    public Object[] toArray() {
      return new Object[]{fInstance, fComponent};
    }

    @Override
    public SynchronousElementaryEnvironmentComponentInstancePattern.Match toImmutable() {
      return isMutable() ? newMatch(fInstance, fComponent) : this;
    }

    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"instance\"=" + prettyPrintValue(fInstance) + ", ");
      result.append("\"component\"=" + prettyPrintValue(fComponent));
      return result.toString();
    }

    @Override
    public int hashCode() {
      return Objects.hash(fInstance, fComponent);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof SynchronousElementaryEnvironmentComponentInstancePattern.Match)) {
          SynchronousElementaryEnvironmentComponentInstancePattern.Match other = (SynchronousElementaryEnvironmentComponentInstancePattern.Match) obj;
          return Objects.equals(fInstance, other.fInstance) && Objects.equals(fComponent, other.fComponent);
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
    public SynchronousElementaryEnvironmentComponentInstancePattern specification() {
      return SynchronousElementaryEnvironmentComponentInstancePattern.instance();
    }

    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static SynchronousElementaryEnvironmentComponentInstancePattern.Match newEmptyMatch() {
      return new Mutable(null, null);
    }

    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static SynchronousElementaryEnvironmentComponentInstancePattern.Match newMutableMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      return new Mutable(pInstance, pComponent);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static SynchronousElementaryEnvironmentComponentInstancePattern.Match newMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      return new Immutable(pInstance, pComponent);
    }

    private static final class Mutable extends SynchronousElementaryEnvironmentComponentInstancePattern.Match {
      Mutable(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
        super(pInstance, pComponent);
      }

      @Override
      public boolean isMutable() {
        return true;
      }
    }

    private static final class Immutable extends SynchronousElementaryEnvironmentComponentInstancePattern.Match {
      Immutable(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
        super(pInstance, pComponent);
      }

      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }

  /**
   * Generated pattern matcher API of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.SynchronousElementaryEnvironmentComponentInstancePattern pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern SynchronousElementaryEnvironmentComponentInstancePattern  (instance : ElementaryEnvironmentComponentInstance, component : EnvironmentSynchronousCompositeComponent){
   * 	ElementaryEnvironmentComponentInstance (instance);
   * 	EnvironmentSynchronousCompositeComponent(component);
   * 	EnvironmentSynchronousCompositeComponent.environmentComponents(component,instance);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see SynchronousElementaryEnvironmentComponentInstancePattern
   * 
   */
  public static class Matcher extends BaseMatcher<SynchronousElementaryEnvironmentComponentInstancePattern.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static SynchronousElementaryEnvironmentComponentInstancePattern.Matcher on(final ViatraQueryEngine engine) {
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
    public static SynchronousElementaryEnvironmentComponentInstancePattern.Matcher create() {
      return new Matcher();
    }

    private static final int POSITION_INSTANCE = 0;

    private static final int POSITION_COMPONENT = 1;

    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(SynchronousElementaryEnvironmentComponentInstancePattern.Matcher.class);

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
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<SynchronousElementaryEnvironmentComponentInstancePattern.Match> getAllMatches(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      return rawStreamAllMatches(new Object[]{pInstance, pComponent}).collect(Collectors.toSet());
    }

    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<SynchronousElementaryEnvironmentComponentInstancePattern.Match> streamAllMatches(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      return rawStreamAllMatches(new Object[]{pInstance, pComponent});
    }

    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<SynchronousElementaryEnvironmentComponentInstancePattern.Match> getOneArbitraryMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      return rawGetOneArbitraryMatch(new Object[]{pInstance, pComponent});
    }

    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      return rawHasMatch(new Object[]{pInstance, pComponent});
    }

    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      return rawCountMatches(new Object[]{pInstance, pComponent});
    }

    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent, final Consumer<? super SynchronousElementaryEnvironmentComponentInstancePattern.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pInstance, pComponent}, processor);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public SynchronousElementaryEnvironmentComponentInstancePattern.Match newMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentSynchronousCompositeComponent pComponent) {
      return SynchronousElementaryEnvironmentComponentInstancePattern.Match.newMatch(pInstance, pComponent);
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<ElementaryEnvironmentComponentInstance> rawStreamAllValuesOfinstance(final Object[] parameters) {
      return rawStreamAllValues(POSITION_INSTANCE, parameters).map(ElementaryEnvironmentComponentInstance.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfinstance() {
      return rawStreamAllValuesOfinstance(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfinstance() {
      return rawStreamAllValuesOfinstance(emptyArray());
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfinstance(final SynchronousElementaryEnvironmentComponentInstancePattern.Match partialMatch) {
      return rawStreamAllValuesOfinstance(partialMatch.toArray());
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfinstance(final EnvironmentSynchronousCompositeComponent pComponent) {
      return rawStreamAllValuesOfinstance(new Object[]{null, pComponent});
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfinstance(final SynchronousElementaryEnvironmentComponentInstancePattern.Match partialMatch) {
      return rawStreamAllValuesOfinstance(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfinstance(final EnvironmentSynchronousCompositeComponent pComponent) {
      return rawStreamAllValuesOfinstance(new Object[]{null, pComponent}).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<EnvironmentSynchronousCompositeComponent> rawStreamAllValuesOfcomponent(final Object[] parameters) {
      return rawStreamAllValues(POSITION_COMPONENT, parameters).map(EnvironmentSynchronousCompositeComponent.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentSynchronousCompositeComponent> getAllValuesOfcomponent() {
      return rawStreamAllValuesOfcomponent(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<EnvironmentSynchronousCompositeComponent> streamAllValuesOfcomponent() {
      return rawStreamAllValuesOfcomponent(emptyArray());
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<EnvironmentSynchronousCompositeComponent> streamAllValuesOfcomponent(final SynchronousElementaryEnvironmentComponentInstancePattern.Match partialMatch) {
      return rawStreamAllValuesOfcomponent(partialMatch.toArray());
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<EnvironmentSynchronousCompositeComponent> streamAllValuesOfcomponent(final ElementaryEnvironmentComponentInstance pInstance) {
      return rawStreamAllValuesOfcomponent(new Object[]{pInstance, null});
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentSynchronousCompositeComponent> getAllValuesOfcomponent(final SynchronousElementaryEnvironmentComponentInstancePattern.Match partialMatch) {
      return rawStreamAllValuesOfcomponent(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentSynchronousCompositeComponent> getAllValuesOfcomponent(final ElementaryEnvironmentComponentInstance pInstance) {
      return rawStreamAllValuesOfcomponent(new Object[]{pInstance, null}).collect(Collectors.toSet());
    }

    @Override
    protected SynchronousElementaryEnvironmentComponentInstancePattern.Match tupleToMatch(final Tuple t) {
      try {
          return SynchronousElementaryEnvironmentComponentInstancePattern.Match.newMatch((ElementaryEnvironmentComponentInstance) t.get(POSITION_INSTANCE), (EnvironmentSynchronousCompositeComponent) t.get(POSITION_COMPONENT));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }

    @Override
    protected SynchronousElementaryEnvironmentComponentInstancePattern.Match arrayToMatch(final Object[] match) {
      try {
          return SynchronousElementaryEnvironmentComponentInstancePattern.Match.newMatch((ElementaryEnvironmentComponentInstance) match[POSITION_INSTANCE], (EnvironmentSynchronousCompositeComponent) match[POSITION_COMPONENT]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }

    @Override
    protected SynchronousElementaryEnvironmentComponentInstancePattern.Match arrayToMatchMutable(final Object[] match) {
      try {
          return SynchronousElementaryEnvironmentComponentInstancePattern.Match.newMutableMatch((ElementaryEnvironmentComponentInstance) match[POSITION_INSTANCE], (EnvironmentSynchronousCompositeComponent) match[POSITION_COMPONENT]);
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
    public static IQuerySpecification<SynchronousElementaryEnvironmentComponentInstancePattern.Matcher> querySpecification() {
      return SynchronousElementaryEnvironmentComponentInstancePattern.instance();
    }
  }

  private SynchronousElementaryEnvironmentComponentInstancePattern() {
    super(GeneratedPQuery.INSTANCE);
  }

  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static SynchronousElementaryEnvironmentComponentInstancePattern instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }

  @Override
  protected SynchronousElementaryEnvironmentComponentInstancePattern.Matcher instantiate(final ViatraQueryEngine engine) {
    return SynchronousElementaryEnvironmentComponentInstancePattern.Matcher.on(engine);
  }

  @Override
  public SynchronousElementaryEnvironmentComponentInstancePattern.Matcher instantiate() {
    return SynchronousElementaryEnvironmentComponentInstancePattern.Matcher.create();
  }

  @Override
  public SynchronousElementaryEnvironmentComponentInstancePattern.Match newEmptyMatch() {
    return SynchronousElementaryEnvironmentComponentInstancePattern.Match.newEmptyMatch();
  }

  @Override
  public SynchronousElementaryEnvironmentComponentInstancePattern.Match newMatch(final Object... parameters) {
    return SynchronousElementaryEnvironmentComponentInstancePattern.Match.newMatch((hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance) parameters[0], (hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent) parameters[1]);
  }

  /**
   * Inner class allowing the singleton instance of {@link SynchronousElementaryEnvironmentComponentInstancePattern} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link SynchronousElementaryEnvironmentComponentInstancePattern#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final SynchronousElementaryEnvironmentComponentInstancePattern INSTANCE = new SynchronousElementaryEnvironmentComponentInstancePattern();

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
    private static final SynchronousElementaryEnvironmentComponentInstancePattern.GeneratedPQuery INSTANCE = new GeneratedPQuery();

    private final PParameter parameter_instance = new PParameter("instance", "hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")), PParameterDirection.INOUT);

    private final PParameter parameter_component = new PParameter("component", "hu.bme.mit.gamma.environment.model.EnvironmentSynchronousCompositeComponent", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("www.mit.bme.hu/gamma/environment/Model", "EnvironmentSynchronousCompositeComponent")), PParameterDirection.INOUT);

    private final List<PParameter> parameters = Arrays.asList(parameter_instance, parameter_component);

    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }

    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.SynchronousElementaryEnvironmentComponentInstancePattern";
    }

    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("instance","component");
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
          PVariable var_instance = body.getOrCreateVariableByName("instance");
          PVariable var_component = body.getOrCreateVariableByName("component");
          new TypeConstraint(body, Tuples.flatTupleOf(var_instance), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_component), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentSynchronousCompositeComponent")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_instance, parameter_instance),
             new ExportedParameter(body, var_component, parameter_component)
          ));
          // 	ElementaryEnvironmentComponentInstance (instance)
          new TypeConstraint(body, Tuples.flatTupleOf(var_instance), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")));
          // 	EnvironmentSynchronousCompositeComponent(component)
          new TypeConstraint(body, Tuples.flatTupleOf(var_component), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentSynchronousCompositeComponent")));
          // 	EnvironmentSynchronousCompositeComponent.environmentComponents(component,instance)
          new TypeConstraint(body, Tuples.flatTupleOf(var_component), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentSynchronousCompositeComponent")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_component, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("www.mit.bme.hu/gamma/environment/Model", "AbstractEnvironmentCompositeComponent", "environmentComponents")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentComponentInstance")));
          new Equality(body, var__virtual_0_, var_instance);
          bodies.add(body);
      }
      return bodies;
    }
  }
}
