package com.BerkeTurk;

import org.apache.maven.plugin.MojoExecutionException;

public interface VersionBeholder {

    String getVersion(String command) throws MojoExecutionException;
}
