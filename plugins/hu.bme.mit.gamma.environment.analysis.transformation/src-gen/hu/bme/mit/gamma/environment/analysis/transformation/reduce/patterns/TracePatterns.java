/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.analysis.transformation/src/hu/bme/mit/gamma/environment/analysis/transformation/reduce/patterns/TracePatterns.vql
 */
package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in TracePatterns.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file TracePatterns.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns, the group contains the definition of the following patterns: <ul>
 * <li>EnvironmentAsynchronousCompositeCopmponentTracePattern</li>
 * <li>EnvironmentSynchronousCompositeCopmponentTracePattern</li>
 * <li>EnvironmentCascadeCompositeComponentTracePattern</li>
 * <li>EnvironmentAsynchronousCompositeInstanceTracePattern</li>
 * <li>EnvironmentSynchronousCompositeInstanceTracePattern</li>
 * <li>EnvironmentCascadeCompositeInstanceTracePattern</li>
 * <li>ElementaryEnvironmentComponentInstanceTracePattern</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class TracePatterns extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static TracePatterns instance() {
    if (INSTANCE == null) {
        INSTANCE = new TracePatterns();
    }
    return INSTANCE;
  }

  private static TracePatterns INSTANCE;

  private TracePatterns() {
    querySpecifications.add(EnvironmentAsynchronousCompositeCopmponentTracePattern.instance());
    querySpecifications.add(EnvironmentSynchronousCompositeCopmponentTracePattern.instance());
    querySpecifications.add(EnvironmentCascadeCompositeComponentTracePattern.instance());
    querySpecifications.add(EnvironmentAsynchronousCompositeInstanceTracePattern.instance());
    querySpecifications.add(EnvironmentSynchronousCompositeInstanceTracePattern.instance());
    querySpecifications.add(EnvironmentCascadeCompositeInstanceTracePattern.instance());
    querySpecifications.add(ElementaryEnvironmentComponentInstanceTracePattern.instance());
  }

  public EnvironmentAsynchronousCompositeCopmponentTracePattern getEnvironmentAsynchronousCompositeCopmponentTracePattern() {
    return EnvironmentAsynchronousCompositeCopmponentTracePattern.instance();
  }

  public EnvironmentAsynchronousCompositeCopmponentTracePattern.Matcher getEnvironmentAsynchronousCompositeCopmponentTracePattern(final ViatraQueryEngine engine) {
    return EnvironmentAsynchronousCompositeCopmponentTracePattern.Matcher.on(engine);
  }

  public EnvironmentSynchronousCompositeCopmponentTracePattern getEnvironmentSynchronousCompositeCopmponentTracePattern() {
    return EnvironmentSynchronousCompositeCopmponentTracePattern.instance();
  }

  public EnvironmentSynchronousCompositeCopmponentTracePattern.Matcher getEnvironmentSynchronousCompositeCopmponentTracePattern(final ViatraQueryEngine engine) {
    return EnvironmentSynchronousCompositeCopmponentTracePattern.Matcher.on(engine);
  }

  public EnvironmentCascadeCompositeComponentTracePattern getEnvironmentCascadeCompositeComponentTracePattern() {
    return EnvironmentCascadeCompositeComponentTracePattern.instance();
  }

  public EnvironmentCascadeCompositeComponentTracePattern.Matcher getEnvironmentCascadeCompositeComponentTracePattern(final ViatraQueryEngine engine) {
    return EnvironmentCascadeCompositeComponentTracePattern.Matcher.on(engine);
  }

  public EnvironmentAsynchronousCompositeInstanceTracePattern getEnvironmentAsynchronousCompositeInstanceTracePattern() {
    return EnvironmentAsynchronousCompositeInstanceTracePattern.instance();
  }

  public EnvironmentAsynchronousCompositeInstanceTracePattern.Matcher getEnvironmentAsynchronousCompositeInstanceTracePattern(final ViatraQueryEngine engine) {
    return EnvironmentAsynchronousCompositeInstanceTracePattern.Matcher.on(engine);
  }

  public EnvironmentSynchronousCompositeInstanceTracePattern getEnvironmentSynchronousCompositeInstanceTracePattern() {
    return EnvironmentSynchronousCompositeInstanceTracePattern.instance();
  }

  public EnvironmentSynchronousCompositeInstanceTracePattern.Matcher getEnvironmentSynchronousCompositeInstanceTracePattern(final ViatraQueryEngine engine) {
    return EnvironmentSynchronousCompositeInstanceTracePattern.Matcher.on(engine);
  }

  public EnvironmentCascadeCompositeInstanceTracePattern getEnvironmentCascadeCompositeInstanceTracePattern() {
    return EnvironmentCascadeCompositeInstanceTracePattern.instance();
  }

  public EnvironmentCascadeCompositeInstanceTracePattern.Matcher getEnvironmentCascadeCompositeInstanceTracePattern(final ViatraQueryEngine engine) {
    return EnvironmentCascadeCompositeInstanceTracePattern.Matcher.on(engine);
  }

  public ElementaryEnvironmentComponentInstanceTracePattern getElementaryEnvironmentComponentInstanceTracePattern() {
    return ElementaryEnvironmentComponentInstanceTracePattern.instance();
  }

  public ElementaryEnvironmentComponentInstanceTracePattern.Matcher getElementaryEnvironmentComponentInstanceTracePattern(final ViatraQueryEngine engine) {
    return ElementaryEnvironmentComponentInstanceTracePattern.Matcher.on(engine);
  }
}
