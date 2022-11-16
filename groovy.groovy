/*
INF-6381
DEV-k8s-execute

This jobs is allowing developer to execute manually command to container in kubernetes -dev namespace 
This is also a testing playground before we make one to PROD

*/

projMap = ["namespace": "${NAMESPACE}", "project": "${PROJ}", "overlays": "${OVERLAYS}"]
command = COMMAND
changelog = CHANGELOG.trim().replaceAll("\n+", "\n");
pkgs = 
currentBuild.displayName = "#" + currentBuild.number + " - exec -" + NAMESPACE+":"+PROJ+":"+OVERLAYS.split()

stage("Pre-check") {
    if (changelog == "") {
        error("CHANGELOG EMPTY")
    } else {
        changelog = utils.expandJiraLink(changelog)
    }
    // check if env is dev
    if (JOB_NAME == "DEV-k8s-execute") {
        projMap["overlays"].each { overlay ->
            if ( ! (overlay.startsWith("dev") || overlay.startsWith("test") || overlay.startsWith("stag")) ) {
                error("INAPPROPRIATE ENV")
            }
        }
    }
    // check if package is accessible

}

stage("Execute") {

}

stage("Check service") {

}

stage("Finish") {

}