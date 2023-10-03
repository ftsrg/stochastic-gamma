/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.validation/src/hu/bme/mit/gamma/environment/validation/ErrorPatterns.vql
 */
package hu.bme.mit.gamma.environment.validation;

import hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance;
import hu.bme.mit.gamma.statechart.composite.SimpleChannel;
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
 *         pattern EnvironmentChannelInterface(channel: SimpleChannel, source : ElementaryEnvironmentComponentInstance, target : SynchronousComponentInstance) {
 *         	Channel.providedPort.instance(channel,source);
 *         	SimpleChannel.requiredPort.instance(channel,target);
 *         	
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class EnvironmentChannelInterface extends BaseGeneratedEMFQuerySpecification<EnvironmentChannelInterface.Matcher> {
  /**
   * Pattern-specific match representation of the hu.bme.mit.gamma.environment.validation.EnvironmentChannelInterface pattern,
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
    private SimpleChannel fChannel;

    private ElementaryEnvironmentComponentInstance fSource;

    private SynchronousComponentInstance fTarget;

    private static List<String> parameterNames = makeImmutableList("channel", "source", "target");

    private Match(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      this.fChannel = pChannel;
      this.fSource = pSource;
      this.fTarget = pTarget;
    }

    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "channel": return this.fChannel;
          case "source": return this.fSource;
          case "target": return this.fTarget;
          default: return null;
      }
    }

    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fChannel;
          case 1: return this.fSource;
          case 2: return this.fTarget;
          default: return null;
      }
    }

    public SimpleChannel getChannel() {
      return this.fChannel;
    }

    public ElementaryEnvironmentComponentInstance getSource() {
      return this.fSource;
    }

    public SynchronousComponentInstance getTarget() {
      return this.fTarget;
    }

    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("channel".equals(parameterName) ) {
          this.fChannel = (SimpleChannel) newValue;
          return true;
      }
      if ("source".equals(parameterName) ) {
          this.fSource = (ElementaryEnvironmentComponentInstance) newValue;
          return true;
      }
      if ("target".equals(parameterName) ) {
          this.fTarget = (SynchronousComponentInstance) newValue;
          return true;
      }
      return false;
    }

    public void setChannel(final SimpleChannel pChannel) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fChannel = pChannel;
    }

    public void setSource(final ElementaryEnvironmentComponentInstance pSource) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSource = pSource;
    }

    public void setTarget(final SynchronousComponentInstance pTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTarget = pTarget;
    }

    @Override
    public String patternName() {
      return "hu.bme.mit.gamma.environment.validation.EnvironmentChannelInterface";
    }

    @Override
    public List<String> parameterNames() {
      return EnvironmentChannelInterface.Match.parameterNames;
    }

    @Override
    public Object[] toArray() {
      return new Object[]{fChannel, fSource, fTarget};
    }

    @Override
    public EnvironmentChannelInterface.Match toImmutable() {
      return isMutable() ? newMatch(fChannel, fSource, fTarget) : this;
    }

    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"channel\"=" + prettyPrintValue(fChannel) + ", ");
      result.append("\"source\"=" + prettyPrintValue(fSource) + ", ");
      result.append("\"target\"=" + prettyPrintValue(fTarget));
      return result.toString();
    }

    @Override
    public int hashCode() {
      return Objects.hash(fChannel, fSource, fTarget);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof EnvironmentChannelInterface.Match)) {
          EnvironmentChannelInterface.Match other = (EnvironmentChannelInterface.Match) obj;
          return Objects.equals(fChannel, other.fChannel) && Objects.equals(fSource, other.fSource) && Objects.equals(fTarget, other.fTarget);
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
    public EnvironmentChannelInterface specification() {
      return EnvironmentChannelInterface.instance();
    }

    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static EnvironmentChannelInterface.Match newEmptyMatch() {
      return new Mutable(null, null, null);
    }

    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static EnvironmentChannelInterface.Match newMutableMatch(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return new Mutable(pChannel, pSource, pTarget);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static EnvironmentChannelInterface.Match newMatch(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return new Immutable(pChannel, pSource, pTarget);
    }

    private static final class Mutable extends EnvironmentChannelInterface.Match {
      Mutable(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
        super(pChannel, pSource, pTarget);
      }

      @Override
      public boolean isMutable() {
        return true;
      }
    }

    private static final class Immutable extends EnvironmentChannelInterface.Match {
      Immutable(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
        super(pChannel, pSource, pTarget);
      }

      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }

  /**
   * Generated pattern matcher API of the hu.bme.mit.gamma.environment.validation.EnvironmentChannelInterface pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern EnvironmentChannelInterface(channel: SimpleChannel, source : ElementaryEnvironmentComponentInstance, target : SynchronousComponentInstance) {
   * 	Channel.providedPort.instance(channel,source);
   * 	SimpleChannel.requiredPort.instance(channel,target);
   * 	
   * }
   * </pre></code>
   * 
   * @see Match
   * @see EnvironmentChannelInterface
   * 
   */
  public static class Matcher extends BaseMatcher<EnvironmentChannelInterface.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static EnvironmentChannelInterface.Matcher on(final ViatraQueryEngine engine) {
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
    public static EnvironmentChannelInterface.Matcher create() {
      return new Matcher();
    }

    private static final int POSITION_CHANNEL = 0;

    private static final int POSITION_SOURCE = 1;

    private static final int POSITION_TARGET = 2;

    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(EnvironmentChannelInterface.Matcher.class);

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
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<EnvironmentChannelInterface.Match> getAllMatches(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawStreamAllMatches(new Object[]{pChannel, pSource, pTarget}).collect(Collectors.toSet());
    }

    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<EnvironmentChannelInterface.Match> streamAllMatches(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawStreamAllMatches(new Object[]{pChannel, pSource, pTarget});
    }

    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<EnvironmentChannelInterface.Match> getOneArbitraryMatch(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawGetOneArbitraryMatch(new Object[]{pChannel, pSource, pTarget});
    }

    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawHasMatch(new Object[]{pChannel, pSource, pTarget});
    }

    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawCountMatches(new Object[]{pChannel, pSource, pTarget});
    }

    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget, final Consumer<? super EnvironmentChannelInterface.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pChannel, pSource, pTarget}, processor);
    }

    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pChannel the fixed value of pattern parameter channel, or null if not bound.
     * @param pSource the fixed value of pattern parameter source, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public EnvironmentChannelInterface.Match newMatch(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return EnvironmentChannelInterface.Match.newMatch(pChannel, pSource, pTarget);
    }

    /**
     * Retrieve the set of values that occur in matches for channel.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<SimpleChannel> rawStreamAllValuesOfchannel(final Object[] parameters) {
      return rawStreamAllValues(POSITION_CHANNEL, parameters).map(SimpleChannel.class::cast);
    }

    /**
     * Retrieve the set of values that occur in matches for channel.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<SimpleChannel> getAllValuesOfchannel() {
      return rawStreamAllValuesOfchannel(emptyArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for channel.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<SimpleChannel> streamAllValuesOfchannel() {
      return rawStreamAllValuesOfchannel(emptyArray());
    }

    /**
     * Retrieve the set of values that occur in matches for channel.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<SimpleChannel> streamAllValuesOfchannel(final EnvironmentChannelInterface.Match partialMatch) {
      return rawStreamAllValuesOfchannel(partialMatch.toArray());
    }

    /**
     * Retrieve the set of values that occur in matches for channel.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<SimpleChannel> streamAllValuesOfchannel(final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawStreamAllValuesOfchannel(new Object[]{null, pSource, pTarget});
    }

    /**
     * Retrieve the set of values that occur in matches for channel.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<SimpleChannel> getAllValuesOfchannel(final EnvironmentChannelInterface.Match partialMatch) {
      return rawStreamAllValuesOfchannel(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for channel.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<SimpleChannel> getAllValuesOfchannel(final ElementaryEnvironmentComponentInstance pSource, final SynchronousComponentInstance pTarget) {
      return rawStreamAllValuesOfchannel(new Object[]{null, pSource, pTarget}).collect(Collectors.toSet());
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
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfsource(final EnvironmentChannelInterface.Match partialMatch) {
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
    public Stream<ElementaryEnvironmentComponentInstance> streamAllValuesOfsource(final SimpleChannel pChannel, final SynchronousComponentInstance pTarget) {
      return rawStreamAllValuesOfsource(new Object[]{pChannel, null, pTarget});
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfsource(final EnvironmentChannelInterface.Match partialMatch) {
      return rawStreamAllValuesOfsource(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for source.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<ElementaryEnvironmentComponentInstance> getAllValuesOfsource(final SimpleChannel pChannel, final SynchronousComponentInstance pTarget) {
      return rawStreamAllValuesOfsource(new Object[]{pChannel, null, pTarget}).collect(Collectors.toSet());
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
    public Stream<SynchronousComponentInstance> streamAllValuesOftarget(final EnvironmentChannelInterface.Match partialMatch) {
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
    public Stream<SynchronousComponentInstance> streamAllValuesOftarget(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource) {
      return rawStreamAllValuesOftarget(new Object[]{pChannel, pSource, null});
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<SynchronousComponentInstance> getAllValuesOftarget(final EnvironmentChannelInterface.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray()).collect(Collectors.toSet());
    }

    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<SynchronousComponentInstance> getAllValuesOftarget(final SimpleChannel pChannel, final ElementaryEnvironmentComponentInstance pSource) {
      return rawStreamAllValuesOftarget(new Object[]{pChannel, pSource, null}).collect(Collectors.toSet());
    }

    @Override
    protected EnvironmentChannelInterface.Match tupleToMatch(final Tuple t) {
      try {
          return EnvironmentChannelInterface.Match.newMatch((SimpleChannel) t.get(POSITION_CHANNEL), (ElementaryEnvironmentComponentInstance) t.get(POSITION_SOURCE), (SynchronousComponentInstance) t.get(POSITION_TARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }

    @Override
    protected EnvironmentChannelInterface.Match arrayToMatch(final Object[] match) {
      try {
          return EnvironmentChannelInterface.Match.newMatch((SimpleChannel) match[POSITION_CHANNEL], (ElementaryEnvironmentComponentInstance) match[POSITION_SOURCE], (SynchronousComponentInstance) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }

    @Override
    protected EnvironmentChannelInterface.Match arrayToMatchMutable(final Object[] match) {
      try {
          return EnvironmentChannelInterface.Match.newMutableMatch((SimpleChannel) match[POSITION_CHANNEL], (ElementaryEnvironmentComponentInstance) match[POSITION_SOURCE], (SynchronousComponentInstance) match[POSITION_TARGET]);
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
    public static IQuerySpecification<EnvironmentChannelInterface.Matcher> querySpecification() {
      return EnvironmentChannelInterface.instance();
    }
  }

  private EnvironmentChannelInterface() {
    super(GeneratedPQuery.INSTANCE);
  }

  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static EnvironmentChannelInterface instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }

  @Override
  protected EnvironmentChannelInterface.Matcher instantiate(final ViatraQueryEngine engine) {
    return EnvironmentChannelInterface.Matcher.on(engine);
  }

  @Override
  public EnvironmentChannelInterface.Matcher instantiate() {
    return EnvironmentChannelInterface.Matcher.create();
  }

  @Override
  public EnvironmentChannelInterface.Match newEmptyMatch() {
    return EnvironmentChannelInterface.Match.newEmptyMatch();
  }

  @Override
  public EnvironmentChannelInterface.Match newMatch(final Object... parameters) {
    return EnvironmentChannelInterface.Match.newMatch((hu.bme.mit.gamma.statechart.composite.SimpleChannel) parameters[0], (hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance) parameters[1], (hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance) parameters[2]);
  }

  /**
   * Inner class allowing the singleton instance of {@link EnvironmentChannelInterface} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link EnvironmentChannelInterface#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final EnvironmentChannelInterface INSTANCE = new EnvironmentChannelInterface();

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
    private static final EnvironmentChannelInterface.GeneratedPQuery INSTANCE = new GeneratedPQuery();

    private final PParameter parameter_channel = new PParameter("channel", "hu.bme.mit.gamma.statechart.composite.SimpleChannel", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SimpleChannel")), PParameterDirection.INOUT);

    private final PParameter parameter_source = new PParameter("source", "hu.bme.mit.gamma.environment.model.ElementaryEnvironmentComponentInstance", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")), PParameterDirection.INOUT);

    private final PParameter parameter_target = new PParameter("target", "hu.bme.mit.gamma.statechart.composite.SynchronousComponentInstance", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SynchronousComponentInstance")), PParameterDirection.INOUT);

    private final List<PParameter> parameters = Arrays.asList(parameter_channel, parameter_source, parameter_target);

    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }

    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.gamma.environment.validation.EnvironmentChannelInterface";
    }

    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("channel","source","target");
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
          PVariable var_channel = body.getOrCreateVariableByName("channel");
          PVariable var_source = body.getOrCreateVariableByName("source");
          PVariable var_target = body.getOrCreateVariableByName("target");
          new TypeConstraint(body, Tuples.flatTupleOf(var_channel), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SimpleChannel")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_source), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("www.mit.bme.hu/gamma/environment/Model", "ElementaryEnvironmentComponentInstance")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SynchronousComponentInstance")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_channel, parameter_channel),
             new ExportedParameter(body, var_source, parameter_source),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	Channel.providedPort.instance(channel,source)
          new TypeConstraint(body, Tuples.flatTupleOf(var_channel), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "Channel")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_channel, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "Channel", "providedPort")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "InstancePortReference")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "InstancePortReference", "instance")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "ComponentInstance")));
          new Equality(body, var__virtual_1_, var_source);
          // 	SimpleChannel.requiredPort.instance(channel,target)
          new TypeConstraint(body, Tuples.flatTupleOf(var_channel), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SimpleChannel")));
          PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_channel, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "SimpleChannel", "requiredPort")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "InstancePortReference")));
          PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_2_, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "InstancePortReference", "instance")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_3_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.mit.bme.hu/gamma/statechart/Model/Composite", "ComponentInstance")));
          new Equality(body, var__virtual_3_, var_target);
          bodies.add(body);
      }
      return bodies;
    }
  }
}
