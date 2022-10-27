stage('Pre-test') {
    node(any)
       sh(script: "/bin/bash /root/jenkins/a.sh")
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