/**
 * Generated from platform:/resource/hu.bme.mit.gamma.environment.validation/src/hu/bme/mit/gamma/environment/validation/ErrorPatterns.vql
 */
package hu.bme.mit.gamma.environment.validation;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;

/**
 * A pattern group formed of all public patterns defined in ErrorPatterns.vql.
 * 
 * <p>Use the static instance as any {@link interface org.eclipse.viatra.query.runtime.api.IQueryGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ErrorPatterns.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.gamma.environment.validation, the group contains the definition of the following patterns: <ul>
 * <li>EnvironmentChannelInterface</li>
 * </ul>
 * 
 * @see IQueryGroup
 * 
 */
@SuppressWarnings("all")
public final class ErrorPatterns extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryRuntimeException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ErrorPatterns instance() {
    if (INSTANCE == null) {
        INSTANCE = new ErrorPatterns();
    }
    return INSTANCE;
  }

  private static ErrorPatterns INSTANCE;

  private ErrorPatterns() {
    querySpecifications.add(EnvironmentChannelInterface.instance());
  }

  public EnvironmentChannelInterface getEnvironmentChannelInterface() {
    return EnvironmentChannelInterface.instance();
  }

  public EnvironmentChannelInterface.Matcher getEnvironmentChannelInterface(final ViatraQueryEngine engine) {
    return EnvironmentChannelInterface.Matcher.on(engine);
  }
}
