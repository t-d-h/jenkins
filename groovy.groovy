/*
INF-6381
DEV-k8s-execute

This jobs is allowing developer to execute manually command to container in kubernetes -dev namespace 
This is also a testing playground before we make one to PROD

*/
import java.util.regex.Matcher
import java.util.regex.Pattern

projMap = ["namespace": "${NAMESPACE}", "project": "${PROJ}", "overlay": "${OVERLAYS}"]
command = COMMAND
changelog = CHANGELOG.trim().replaceAll("\n+", "\n");
pkgpath = null
//check if there's any attachment
if (COMMAND.contains('://')) {
    Pattern urlPattern = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",Pattern.CASE_INSENSITIVE);
    Matcher matcher = urlPattern.matcher(command);
    while (matcher.find()) {
        pkgpath = matcher.group()
        println "${pkgpath}"
        }
}

currentBuild.displayName = "#" + currentBuild.number + " - exec -" + NAMESPACE+":"+PROJ+":"+OVERLAYS.split()

stage("Pre-check") {
/*  
    if (changelog == "") {
        error("CHANGELOG EMPTY")
    } else {
        changelog = utils.expandJiraLink(changelog)
    }
*/
    // check if env is dev
    if (JOB_NAME == "DEV-k8s-execute") {
        projMap["overlays"].each { overlay ->
            if ( ! (overlay.startsWith("dev") || overlay.startsWith("test") || overlay.startsWith("stag")) ) {
                error("INAPPROPRIATE ENV")
            }
        }
    }

    // check if pkgpath is exists, if yes, verify the url
    if ("${pkgpath}" != null ) {
        println "its exists, trying to curl it"
        sh(script: "curl -sS --fail -IL $pkgpath >/dev/null")
    } 
    
    // check if the container is running
    println "done"
}

stage("Execute") {

}

stage("Check service") {

}

stage("Finish") {

}