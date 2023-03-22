/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.analysis.transformation/src/hu/bme/mit/gamma/environment/analysis/transformation/reduce/patterns/Patterns.vql
 */
package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
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
 *         pattern CascadeElementaryEnvironmentComponentInstancePattern  (instance : ElementaryEnvironmentComponentInstance, component : EnvironmentCascadeCompositeComponent){
 *         	ElementaryEnvironmentComponentInstance (instance);
 *         	EnvironmentCascadeCompositeComponent(component);
 *         	EnvironmentCascadeCompositeComponent.environmentComponents(component,instance);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class CascadeElementaryEnvironmentComponentInstancePattern extends BaseGeneratedEMFQuerySpecification<CascadeElementaryEnvironmentComponentInstancePattern.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.CascadeElementaryEnvironmentComponentInstancePattern pattern,
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

    private EnvironmentCascadeCompositeComponent fComponent;

    private static List<String> parameterNames = makeImmutableList("instance", "component");

    private Match(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
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

    public EnvironmentCascadeCompositeComponent getComponent() {
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
          this.fComponent = (EnvironmentCascadeCompositeComponent) newValue;
          return true;
      }
      return false;
    }

    public void setInstance(final ElementaryEnvironmentComponentInstance pInstance) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fInstance = pInstance;
    }

    public void setComponent(final EnvironmentCascadeCompositeComponent pComponent) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fComponent = pComponent;
    }

    @Override
    public String patternName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.CascadeElementaryEnvironmentComponentInstancePattern";
    }

    @Override
    public List<String> parameterNames() {
      return CascadeElementaryEnvironmentComponentInstancePattern.Match.parameterNames;
    }

    @Override
    public Object[] toArray() {
      return new Object[]{fInstance, fComponent};
    }

    @Override
    public CascadeElementaryEnvironmentComponentInstancePattern.Match toImmutable() {
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
      if ((obj instanceof CascadeElementaryEnvironmentComponentInstancePattern.Match)) {
          CascadeElementaryEnvironmentComponentInstancePattern.Match other = (CascadeElementaryEnvironmentComponentInstancePattern.Match) obj;
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
    public CascadeElementaryEnvironmentComponentInstancePattern specification() {
      return CascadeElementaryEnvironmentComponentInstancePattern.instance();
    }

    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static CascadeElementaryEnvironmentComponentInstancePattern.Match newEmptyMatch() {
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
    public static CascadeElementaryEnvironmentComponentInstancePattern.Match newMutableMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
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
    public static CascadeElementaryEnvironmentComponentInstancePattern.Match newMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
      return new Immutable(pInstance, pComponent);
    }

    private static final class Mutable extends CascadeElementaryEnvironmentComponentInstancePattern.Match {
      Mutable(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
        super(pInstance, pComponent);
      }

      @Override
      public boolean isMutable() {
        return true;
      }
    }

    private static final class Immutable extends CascadeElementaryEnvironmentComponentInstancePattern.Match {
      Immutable(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
        super(pInstance, pComponent);
      }

      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }

  /**
   * Generated pattern matcher API of the hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.CascadeElementaryEnvironmentComponentInstancePattern pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern CascadeElementaryEnvironmentComponentInstancePattern  (instance : ElementaryEnvironmentComponentInstance, component : EnvironmentCascadeCompositeComponent){
   * 	ElementaryEnvironmentComponentInstance (instance);
   * 	EnvironmentCascadeCompositeComponent(component);
   * 	EnvironmentCascadeCompositeComponent.environmentComponents(component,instance);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see CascadeElementaryEnvironmentComponentInstancePattern
   * 
   */
  public static class Matcher extends BaseMatcher<CascadeElementaryEnvironmentComponentInstancePattern.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static CascadeElementaryEnvironmentComponentInstancePattern.Matcher on(final ViatraQueryEngine engine) {
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
    public static CascadeElementaryEnvironmentComponentInstancePattern.Matcher create() {
      return new Matcher();
    }

    private static final int POSITION_INSTANCE = 0;

    private static final int POSITION_COMPONENT = 1;

    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(CascadeElementaryEnvironmentComponentInstancePattern.Matcher.class);

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
    public Collection<CascadeElementaryEnvironmentComponentInstancePattern.Match> getAllMatches(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
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
    public Stream<CascadeElementaryEnvironmentComponentInstancePattern.Match> streamAllMatches(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
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
    public Optional<CascadeElementaryEnvironmentComponentInstancePattern.Match> getOneArbitraryMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
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
    public boolean hasMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
      return rawHasMatch(new Object[]{pInstance, pComponent});
    }

    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pInstance the fixed value of pattern parameter instance, or null if not bound.
     * @param pComponent the fixed value of pattern parameter component, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
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
    public boolean forOneArbitraryMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent, final Consumer<? super CascadeElementaryEnvironmentComponentInstancePattern.Match> processor) {
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
    public CascadeElementaryEnvironmentComponentInstancePattern.Match newMatch(final ElementaryEnvironmentComponentInstance pInstance, final EnvironmentCascadeCompositeComponent pComponent) {
      return CascadeElementaryEnvironmentComponentInstancePattern.Match.newMatch(pInstance, pComponent);
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
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfinstance(final CascadeElementaryEnvironmentComponentInstancePattern.Match partialMatch) {
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
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfinstance(final EnvironmentCascadeCompositeComponent pComponent) {
      return rawStreamAllValuesOfinstance(new Object[]{null, pComponent});
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfinstance(final CascadeElementaryEnvironmentComponentInstancePattern.Match partialMatch) {
      return rawStreamAllValuesOfinstance(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for instance.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfinstance(final EnvironmentCascadeCompositeComponent pComponent) {
      return rawStreamAllValuesOfinstance(new Object[]{null, pComponent}).collect(Collectors.toSet());
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
    public Stream<EnvironmentCascadeCompositeComponent> streamAllValuesOfcomponent(final CascadeElementaryEnvironmentComponentInstancePattern.Match partialMatch) {
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
    public Stream<EnvironmentCascadeCompositeComponent> streamAllValuesOfcomponent(final ElementaryEnvironmentComponentInstance pInstance) {
      return rawStreamAllValuesOfcomponent(new Object[]{pInstance, null});
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentCascadeCompositeComponent> getAllValuesOfcomponent(final CascadeElementaryEnvironmentComponentInstancePattern.Match partialMatch) {
      return rawStreamAllValuesOfcomponent(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for component.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<EnvironmentCascadeCompositeComponent> getAllValuesOfcomponent(final ElementaryEnvironmentComponentInstance pInstance) {
      return rawStreamAllValuesOfcomponent(new Object[]{pInstance, null}).collect(Collectors.toSet());
    }

    @Override
    protected CascadeElementaryEnvironmentComponentInstancePattern.Match tupleToMatch(final Tuple t) {
      try {
          return CascadeElementaryEnvironmentComponentInstancePattern.Match.newMatch((ElementaryEnvironmentComponentInstance) t.get(POSITION_INSTANCE), (EnvironmentCascadeCompositeComponent) t.get(POSITION_COMPONENT));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }

    @Override
    protected CascadeElementaryEnvironmentComponentInstancePattern.Match arrayToMatch(final Object[] match) {
      try {
          return CascadeElementaryEnvironmentComponentInstancePattern.Match.newMatch((ElementaryEnvironmentComponentInstance) match[POSITION_INSTANCE], (EnvironmentCascadeCompositeComponent) match[POSITION_COMPONENT]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }

    @Override
    protected CascadeElementaryEnvironmentComponentInstancePattern.Match arrayToMatchMutable(final Object[] match) {
      try {
          return CascadeElementaryEnvironmentComponentInstancePattern.Match.newMutableMatch((ElementaryEnvironmentComponentInstance) match[POSITION_INSTANCE], (EnvironmentCascadeCompositeComponent) match[POSITION_COMPONENT]);
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
    public static IQuerySpecification<CascadeElementaryEnvironmentComponentInstancePattern.Matcher> querySpecification() {
      return CascadeElementaryEnvironmentComponentInstancePattern.instance();
    }
  }

  private CascadeElementaryEnvironmentComponentInstancePattern() {
    super(GeneratedPQuery.INSTANCE);
  }

  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static CascadeElementaryEnvironmentComponentInstancePattern instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }

  @Override
  protected CascadeElementaryEnvironmentComponentInstancePattern.Matcher instantiate(final ViatraQueryEngine engine) {
    return CascadeElementaryEnvironmentComponentInstancePattern.Matcher.on(engine);
  }

  @Override
  public CascadeElementaryEnvironmentComponentInstancePattern.Matcher instantiate() {
    return CascadeElementaryEnvironmentComponentInstancePattern.Matcher.create();
  }

  @Override
  public CascadeElementaryEnvironmentComponentInstancePattern.Match newEmptyMatch() {
    return CascadeElementaryEnvironmentComponentInstancePattern.Match.newEmptyMatch();
  }

  @Override
  public CascadeElementaryEnvironmentComponentInstancePattern.Match newMatch(final Object... parameters) {
    return CascadeElementaryEnvironmentComponentInstancePattern.Match.newMatch((hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance) parameters[0], (hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent) parameters[1]);
  }

  /**
   * Inner class allowing the singleton instance of {@link CascadeElementaryEnvironmentComponentInstancePattern} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link CascadeElementaryEnvironmentComponentInstancePattern#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final CascadeElementaryEnvironmentComponentInstancePattern INSTANCE = new CascadeElementaryEnvironmentComponentInstancePattern();

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
    private static final CascadeElementaryEnvironmentComponentInstancePattern.GeneratedPQuery INSTANCE = new GeneratedPQuery();

    private final PParameter parameter_instance = new PParameter("instance", "hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")), PParameterDirection.INOUT);

    private final PParameter parameter_component = new PParameter("component", "hu.bme.mit.gamma.environment.model.EnvironmentCascadeCompositeComponent", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("www.mit.bme.hu/gamma/environment/Model", "EnvironmentCascadeCompositeComponent")), PParameterDirection.INOUT);

    private final List<PParameter> parameters = Arrays.asList(parameter_instance, parameter_component);

    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }

    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns.CascadeElementaryEnvironmentComponentInstancePattern";
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
          new TypeConstraint(body, Tuples.flatTupleOf(var_component), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentCascadeCompositeComponent")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_instance, parameter_instance),
             new ExportedParameter(body, var_component, parameter_component)
          ));
          // 	ElementaryEnvironmentComponentInstance (instance)
          new TypeConstraint(body, Tuples.flatTupleOf(var_instance), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")));
          // 	EnvironmentCascadeCompositeComponent(component)
          new TypeConstraint(body, Tuples.flatTupleOf(var_component), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentCascadeCompositeComponent")));
          // 	EnvironmentCascadeCompositeComponent.environmentComponents(component,instance)
          new TypeConstraint(body, Tuples.flatTupleOf(var_component), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "EnvironmentCascadeCompositeComponent")));
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
