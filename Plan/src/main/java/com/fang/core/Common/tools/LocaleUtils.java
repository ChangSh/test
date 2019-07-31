package com.fang.core.Common.tools;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 国际化资源管理
 * 
 */
public final class LocaleUtils {

  /**
   * 国际化资源文件名后缀。
   */
  private static final String SUFFIX_PROPERTIES_FILE = ".LocaleStrings";

  /**
   * 资源管理集，用于缓存已经生成的某个资源文件对应的LocaleManager，当再次调用时，只需要从缓存中获取即可。 <li>
   * key：资源文件所在包名</li> <li>
   * value：该资源文件对应的LocaleManager</li>
   */
  private static Map<String, LocaleUtils> managers = new ConcurrentHashMap<String, LocaleUtils>();

  /**
   * ResourceBundle
   */
  private ResourceBundle bundle;

  /**
   * Locale
   */
  private Locale locale;
  

  /**
   * 获取资源文件对应的LocaleManager.
   * <p>
   * 说明：相同的资源文件对应同一个LocaleManager，不会重复创建。
   * 
   * @param packageName
   *            资源文件所在包名
   * @return 资源文件对应的LocaleManager
   */
  public static synchronized LocaleUtils getManager(String packageName) {
    LocaleUtils mgr = managers.get(packageName);
    if (mgr == null) {
      mgr = new LocaleUtils(packageName);
      managers.put(packageName, mgr);
    }

    return mgr;
  }

  /**
   * 获取资源文件所对应的LocaleManager。 注意：用该方法时，资源文件必须和clazz放在一个包中。
   * 
   * @param clazz
   *            类名
   * @return 资源文件对应的LocaleManager
   */
  public static LocaleUtils getManager(Class<?> clazz) {
    return getManager(clazz.getPackage().getName());
  }

  /**
   * 创建LocaleManager
   * 
   * @param packageName
   *            资源文件所在包名
   */
  private LocaleUtils(String packageName) {
    String bundleName = packageName + SUFFIX_PROPERTIES_FILE;

    try {
      bundle = ResourceBundle.getBundle(bundleName, Locale.getDefault());
    } catch (MissingResourceException ex) {
      // Try from the current loader (that's the case for trusted apps)
      // Should only be required if using a TC5 style classloader
      // structure
      // where common != shared != server
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      if (cl != null) {
        try {
          bundle = ResourceBundle.getBundle(bundleName,
              Locale.getDefault(), cl);
        } catch (MissingResourceException ex2) {
          // Ignore
        }
      }
    }

    // Get the actual locale, which may be different from the requested one
    if (bundle != null) {
      locale = bundle.getLocale();
    }
  }

  /***
   * 从资源文件中获取资源串。
   * 
   * @param key
   *            资源的key，如：<code>user.name</code>
   * @return 资源串 <li>如果key存在，且对应的值存在，返回资源值</li> <li>如果key存在，对应的值不存在，返回“? key
   *         ?”</li> <li>如果key不存在，抛出NullPointerException异常</li>
   */
  public String getString(String key) {
    try {
      return bundle.getString(key);
    } catch (NullPointerException ne) {
      throw new IllegalArgumentException("Key may not have a null value");
    } catch (MissingResourceException mre) {
      return "? " + key + " ?";
    }
  }

  /**
   * 从资源文件中获取资源串。
   * 
   * @param key
   *            资源的key
   * @param args
   *            参数列表，用于替换资源中的“{0}”,“{1}”
   * @return 资源串
   */
  public String getString(final String key, final Object... args) {
    String value = getString(key);
    if (value == null) {
      value = key;
    }

    MessageFormat mf = new MessageFormat(value);
    mf.setLocale(locale);
    return mf.format(args, new StringBuffer(), null).toString();
  }
}