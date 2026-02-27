package com.pixinator.mbtool.mod;

public interface ModListener {
  // ############################################################
  // # METHODS
  // ############################################################

  public void onItemChange(ModEvent e);

  public void onBlockChange(ModEvent e);
}
