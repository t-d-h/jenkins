stage('Pre-test') {
    node(any) {
        def sout = new StringBuilder(), serr = new StringBuilder()
        def proc = 'ls /badDir'.execute()
        proc.consumeProcessOutput(sout, serr)
        proc.waitForOrKill(1000)
        println "out> $sout\nerr> $serr"
        }
}
 
