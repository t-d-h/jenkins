
stage('Pre-check Stage') {
    agent any
    steps {
        def existsTest = sh(script: "test -f /var/lib/jenkins/workspace/python*", returnStatus: true)
        if (existsTest != 0 ) {
            error("Package not found")
        }
    }
}

stage('Deploy to testing node') {
    sh(script: "scp /var/lib/jenkins/workspace/python* root@192.168.1.180:~")

    node("testing-env") {
        sh(script: "dpkg -i \$(ls -t /root | grep python | head -1)")

        def Check1 = sh(script: "curl http://127.0.0.1:80/",returnStatus: true)
        if (Check1 != 0) {
        error("Your application is not running, test failed")
        }
    }
}

stage('Approve to deploy on prod') {
    echo "Application is running well on Testing environemnt"
    input(message: "Continue deploying to Production?", ok: 'OK')
    // notify to telegram
}

stage('Deploy on first production node') {
   sh(script: "scp /var/lib/jenkins/workspace/python* root@192.168.1.190:~")
    node("prod1") {
        sh(script: "dpkg -i \$(ls -t /root | grep python | head -1)")
    }
    node("nginx") {
        def Check2 = sh(script: "curl http://192.168.1.190:80/",returnStatus: true)
        if (Check2 != 0) {
        //notify to telegram error1
        error("Your application is not running, please revert")
        }
    }
}

stage('Deploy on second production node') {
   sh(script: "scp /var/lib/jenkins/workspace/python* root@192.168.1.191:~")
    node("prod1") {
        sh(script: "dpkg -i \$(ls -t /root | grep python | head -1)")
    }
    node("nginx") {
        def Check3 = sh(script: "curl http://192.168.1.191:80/",returnStatus: true)
        if (Check3 != 0) {
        //notify to telegram error2
        error("Your application is not running, please revert")
        }
    }
}


stage('Post check on Production environment') {

    //send success
    agent { label 'prod-env' }
    steps {
        script {
            def status = sh(returnStatus: true, script: "curl http://127.0.0.1/check > check.txt")
            if (status != "OK") {                
            def output = readFile('check.txt').trim()
            error 'Your application is not running, Emergency'
            }
            sh 'rm check.txt'
        }
    }
}