/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.analysis.transformation/src/hu/bme/mit/gamma/environment/analysis/transformation/reduce/patterns/Patterns.vql
 */
package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in Patterns.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file Patterns.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.gamma.environment.analysis.transformation.reduce.patterns, the group contains the definition of the following patterns: <ul>
 * <li>PackagePattern</li>
 * <li>EnvironmentAsynchronousCompositeComponentPattern</li>
 * <li>EnvironmentSynchronousCompositeComponentPattern</li>
 * <li>EnvironmentCascadeCompositeComponentPattern</li>
 * <li>EnvironmentAsynchronousCompositeComponentInstancePattern</li>
 * <li>EnvironmentSynchronousCompositeComponentInstancePattern</li>
 * <li>EnvironmentCascadeCompositeComponentInstancePattern</li>
 * <li>ElementaryEnvironmentComponentInstancePattern</li>
 * <li>SynchronousElementaryEnvironmentComponentInstancePattern</li>
 * <li>CascadeElementaryEnvironmentComponentInstancePattern</li>
 * <li>AsynchronousElementaryEnvironmentComponentInstancePattern</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class Patterns extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static Patterns instance() {
    if (INSTANCE == null) {
        INSTANCE = new Patterns();
    }
    return INSTANCE;
  }

  private static Patterns INSTANCE;

  private Patterns() {
    querySpecifications.add(PackagePattern.instance());
    querySpecifications.add(EnvironmentAsynchronousCompositeComponentPattern.instance());
    querySpecifications.add(EnvironmentSynchronousCompositeComponentPattern.instance());
    querySpecifications.add(EnvironmentCascadeCompositeComponentPattern.instance());
    querySpecifications.add(EnvironmentAsynchronousCompositeComponentInstancePattern.instance());
    querySpecifications.add(EnvironmentSynchronousCompositeComponentInstancePattern.instance());
    querySpecifications.add(EnvironmentCascadeCompositeComponentInstancePattern.instance());
    querySpecifications.add(ElementaryEnvironmentComponentInstancePattern.instance());
    querySpecifications.add(SynchronousElementaryEnvironmentComponentInstancePattern.instance());
    querySpecifications.add(CascadeElementaryEnvironmentComponentInstancePattern.instance());
    querySpecifications.add(AsynchronousElementaryEnvironmentComponentInstancePattern.instance());
  }

  public PackagePattern getPackagePattern() {
    return PackagePattern.instance();
  }

  public PackagePattern.Matcher getPackagePattern(final ViatraQueryEngine engine) {
    return PackagePattern.Matcher.on(engine);
  }

  public EnvironmentAsynchronousCompositeComponentPattern getEnvironmentAsynchronousCompositeComponentPattern() {
    return EnvironmentAsynchronousCompositeComponentPattern.instance();
  }

  public EnvironmentAsynchronousCompositeComponentPattern.Matcher getEnvironmentAsynchronousCompositeComponentPattern(final ViatraQueryEngine engine) {
    return EnvironmentAsynchronousCompositeComponentPattern.Matcher.on(engine);
  }

  public EnvironmentSynchronousCompositeComponentPattern getEnvironmentSynchronousCompositeComponentPattern() {
    return EnvironmentSynchronousCompositeComponentPattern.instance();
  }

  public EnvironmentSynchronousCompositeComponentPattern.Matcher getEnvironmentSynchronousCompositeComponentPattern(final ViatraQueryEngine engine) {
    return EnvironmentSynchronousCompositeComponentPattern.Matcher.on(engine);
  }

  public EnvironmentCascadeCompositeComponentPattern getEnvironmentCascadeCompositeComponentPattern() {
    return EnvironmentCascadeCompositeComponentPattern.instance();
  }

  public EnvironmentCascadeCompositeComponentPattern.Matcher getEnvironmentCascadeCompositeComponentPattern(final ViatraQueryEngine engine) {
    return EnvironmentCascadeCompositeComponentPattern.Matcher.on(engine);
  }

  public EnvironmentAsynchronousCompositeComponentInstancePattern getEnvironmentAsynchronousCompositeComponentInstancePattern() {
    return EnvironmentAsynchronousCompositeComponentInstancePattern.instance();
  }

  public EnvironmentAsynchronousCompositeComponentInstancePattern.Matcher getEnvironmentAsynchronousCompositeComponentInstancePattern(final ViatraQueryEngine engine) {
    return EnvironmentAsynchronousCompositeComponentInstancePattern.Matcher.on(engine);
  }

  public EnvironmentSynchronousCompositeComponentInstancePattern getEnvironmentSynchronousCompositeComponentInstancePattern() {
    return EnvironmentSynchronousCompositeComponentInstancePattern.instance();
  }

  public EnvironmentSynchronousCompositeComponentInstancePattern.Matcher getEnvironmentSynchronousCompositeComponentInstancePattern(final ViatraQueryEngine engine) {
    return EnvironmentSynchronousCompositeComponentInstancePattern.Matcher.on(engine);
  }

  public EnvironmentCascadeCompositeComponentInstancePattern getEnvironmentCascadeCompositeComponentInstancePattern() {
    return EnvironmentCascadeCompositeComponentInstancePattern.instance();
  }

  public EnvironmentCascadeCompositeComponentInstancePattern.Matcher getEnvironmentCascadeCompositeComponentInstancePattern(final ViatraQueryEngine engine) {
    return EnvironmentCascadeCompositeComponentInstancePattern.Matcher.on(engine);
  }

  public ElementaryEnvironmentComponentInstancePattern getElementaryEnvironmentComponentInstancePattern() {
    return ElementaryEnvironmentComponentInstancePattern.instance();
  }

  public ElementaryEnvironmentComponentInstancePattern.Matcher getElementaryEnvironmentComponentInstancePattern(final ViatraQueryEngine engine) {
    return ElementaryEnvironmentComponentInstancePattern.Matcher.on(engine);
  }

  public SynchronousElementaryEnvironmentComponentInstancePattern getSynchronousElementaryEnvironmentComponentInstancePattern() {
    return SynchronousElementaryEnvironmentComponentInstancePattern.instance();
  }

  public SynchronousElementaryEnvironmentComponentInstancePattern.Matcher getSynchronousElementaryEnvironmentComponentInstancePattern(final ViatraQueryEngine engine) {
    return SynchronousElementaryEnvironmentComponentInstancePattern.Matcher.on(engine);
  }

  public CascadeElementaryEnvironmentComponentInstancePattern getCascadeElementaryEnvironmentComponentInstancePattern() {
    return CascadeElementaryEnvironmentComponentInstancePattern.instance();
  }

  public CascadeElementaryEnvironmentComponentInstancePattern.Matcher getCascadeElementaryEnvironmentComponentInstancePattern(final ViatraQueryEngine engine) {
    return CascadeElementaryEnvironmentComponentInstancePattern.Matcher.on(engine);
  }

  public AsynchronousElementaryEnvironmentComponentInstancePattern getAsynchronousElementaryEnvironmentComponentInstancePattern() {
    return AsynchronousElementaryEnvironmentComponentInstancePattern.instance();
  }

  public AsynchronousElementaryEnvironmentComponentInstancePattern.Matcher getAsynchronousElementaryEnvironmentComponentInstancePattern(final ViatraQueryEngine engine) {
    return AsynchronousElementaryEnvironmentComponentInstancePattern.Matcher.on(engine);
  }
}
