/*
INF-6381


This jobs is allowing developer to execute manually command to container in kubernetes -dev namespace 
This is also a testing playground before we make one to PROD

*/

projMap = ["namespace": "${NAMESPACE}", "project": "${PROJ}", "overlays": OVERLAYS, "command"="${COMMAND}"]
changelog = CHANGELOG.trim().replaceAll("\n+", "\n");

currentBuild.displayName = "#" + currentBuild.number + " - exec -" + NAMESPACE+":"+PROJ+":"+OVERLAYS.split()



stage("Pre-check") {

}

stage("Execute") {

}

stage("Check service") {

}

stage("Finish") {

}