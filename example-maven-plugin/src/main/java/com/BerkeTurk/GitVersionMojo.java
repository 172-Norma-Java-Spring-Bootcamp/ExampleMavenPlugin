package com.BerkeTurk;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;


@Mojo(name = "version", defaultPhase = LifecyclePhase.INITIALIZE)
public class GitVersionMojo extends AbstractMojo {

    @Parameter(property = "git.command", defaultValue = "git rev-parse --short HEAD")
    private String command;

    @Parameter(property = "project", readonly = true)
    private MavenProject project;

    @Inject
    VersionBeholder versionBeholder;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        // call the getVersion method
        String version = versionBeholder.getVersion(command);

        // define a new property in the Maven Project
        project.getProperties().put("exampleVersion", version);

        // Maven Plugins have built in logging too
        getLog().info("Git hash: " + version);
    }

}
