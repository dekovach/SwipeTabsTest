package de.kovachev.android.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public final class TypeFaceLoader
{
  private static AssetManager sAssetManager = null;
  private static Typeface sBrandonLight = null;
  private static Typeface sBrandonMedium = null;
  private static Typeface sTimesBold = null;
  private static Typeface sTimesItalic = null;
  private static Typeface sTimesRoman = null;
  
  public static Typeface getBrandonLight()
  {
    if (sBrandonLight == null) {
      sBrandonLight = Typeface.createFromAsset(sAssetManager, "Brandon_light.otf");
    }
    return sBrandonLight;
  }
  
  public static Typeface getBrandonMedium()
  {
    if (sBrandonMedium == null) {
      sBrandonMedium = Typeface.createFromAsset(sAssetManager, "Brandon_med.otf");
    }
    return sBrandonMedium;
  }
  

  public static void init(AssetManager paramAssetManager)
  {
    sAssetManager = paramAssetManager;
  }
}

