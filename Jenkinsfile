import hudson.FilePath
stage('Pre-check Stage') {
    node("Built-In Node") {
        def existsTest = sh(script: "test -f /var/lib/jenkins/workspace/python*", returnStatus: true)
        if (existsTest != 0 ) {
            error("Package not found")
        }
    }
}

stage('Deploy to testing node') {
    node("Built-In Node") {
        sh(script: "scp /var/lib/jenkins/workspace/python* root@192.168.1.180:~")
    }

    node ("testing-env") {
        def Ins1 = sh(script: "dpkg -i \$(ls -t /root | grep python | head -1)",returnStatus: true)
        if (Ins1 != 0) {
        error("Install failed, please check")
        }

        def Check1 = sh(script: "curl http://127.0.0.1:80/",returnStatus: true)
        if (Check1 != 0) {
        error("Your application is not running, test failed")
        }
    }
}

stage('Approve to deploy on prod') {
    node("Built-In Node") {
        echo "Application is running well on Testing environemnt"
        input(message: "Continue deploying to Production?", ok: 'OK')
        // notify to telegram
    }
}

stage('Deploy on first production node') {
    node("Built-In Node") {
        sh(script: "scp /var/lib/jenkins/workspace/python* root@192.168.1.190:~")
    }

    node ("prod1") {
        def Ins2 = sh(script: "dpkg -i \$(ls -t /root | grep python | head -1)",returnStatus: true)
        if (Ins2 != 0) {
        error("Install failed on Prod1, please check")
        }
    }
    node ("Nginx") {
        def Check2 = sh(script: "curl http://192.168.1.190:80/",returnStatus: true)
        if (Check2 != 0) {
        //notify to telegram error1
        error("Your application is not running, please revert")
        }
    }
}

stage('Deploy on second production node') {
    node("Built-In Node") {
        sh(script: "scp /var/lib/jenkins/workspace/python* root@192.168.1.191:~")
    }
    
    node ("prod2") {
        def Ins3 = sh(script: "dpkg -i \$(ls -t /root | grep python | head -1)",returnStatus: true)
        if (Ins3 != 0) {
        error("Install failed on Prod2, please check")
        }
    }
    node ("Nginx") {
        def Check3 = sh(script: "curl http://192.168.1.191:80/",returnStatus: true)
        if (Check3 != 0) {
        //notify to telegram error2
        error("Your application is not running, please revert")
        }
    }
}
