stage('Pre-test') {
    node(any) {
        def cmd = 'hostname'
        def sout = new StringBuffer(), serr = new StringBuffer()
        def proc = cmd.execute()
        proc.consumeProcessOutput(sout, serr)
        proc.waitForOrKill(1000)
        println sout
}
 
