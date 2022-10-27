stage('Pre-test') {
    node(any) {
        def cmd = 'hostname'
        def sout = new StringBuffer(), serr = new StringBuffer()
        def proc = cmd.execute()
        proc.consumeProcessOutput(sout, serr)
        proc.waitForOrKill(1000)
        println sout
        }
}
stage('Deploy on test environment') { 
    steps {
            echo 'install on dev server'
    }
}
stage('Check on dev environment') { 
    steps {
            echo 'success or not'
    }
}
stage('Deploy on Product') { 
    steps {
            echo 'Install on Prod'
    }
}
stage('Last check') { 
    steps {
            echo 'Done'
        }
}        
