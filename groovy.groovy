/*
INF-6381
DEV-k8s-execute

This jobs is allowing developer to execute manually command to container in kubernetes -dev namespace 
This is also a testing playground before we make one to PROD

*/
import java.util.regex.Matcher
import java.util.regex.Pattern
import hudson.FilePath
import hudson.model.ParametersAction

String overlay = "${OVERLAYS}"
String job_name = "${JOB_NAME}"
String namespace = "${NAMESPACE}"
String command = "${COMMAND}"
String project = "${PROJ}"
String deployment
changelog = CHANGELOG.trim().replaceAll("\n+", "\n");

