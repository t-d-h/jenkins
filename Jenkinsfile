import hudson.FilePath
def pkgName
stage('Build Package') {
    node ("Jenkin-node") {
        git branch: 'main', url: "https://github.com/t-d-h/python-webserver.git"
        sh(script: "rm -f /var/lib/jenkins/workspace/python*")

        sh(script: "dpkg -b /var/lib/jenkins/workspace/Build-Debian-Package/")
        sh(script: "dpkg-name /var/lib/jenkins/workspace/Build-Debian-Package.deb")
        pkgName = sh(script:"ls -t /var/lib/jenkins/workspace/ | grep python", returnStdout: true).trim()
    }
}

stage('Pre-check Stage') {
    node ("Jenkin-node") {
        def existsTest = sh(script: "test -f /var/lib/jenkins/workspace/${pkgName}", returnStatus: true)
        if (existsTest != 0 ) {
            error("Package not found")
        }
    }
}

stage('Deploy to testing node') {
    node ("Jenkin-node") {
        sh(script: "scp /var/lib/jenkins/workspace/${pkgName} root@192.168.1.180:/root")
    }
    
    node ("testing-env") {
        def Ins1 = sh(script: "dpkg -i /root/${pkgName}",returnStatus: true)
        if (Ins1 != 0) {
        error("Install failed, please check")
        }

        sh(script: "systemctl start python-webserver.service")
        def Check1 = sh(script: "curl http://127.0.0.1:80/",returnStatus: true)
        if (Check1 != 0) {
        error("Your application is not running, test failed")
        }
    }
}

stage('Approve to deploy on prod') {
    node ("Jenkin-node") {
        echo "Application is running well on Testing environemnt"
        input(message: "Continue deploying to Production?", ok: 'OK')
        // notify to telegram
    }
}

stage('Deploy on first production node') {
    node ("Jenkin-node") {
        sh(script: "scp /var/lib/jenkins/workspace/${pkgName} root@192.168.1.190:/root")
    }

    node ("Nginx") {
        // switch all traffic to Prod2
        sh(script: "sed 's/server 192.168.1.190/#server 192.168.1.190/g' /etc/nginx/nginx.conf")
        sh(script:"systemctl reload nginx")
    }

    node ("prod1") {
        def Ins2 = sh(script: "dpkg -i /root/${pkgName}",returnStatus: true)
        if (Ins2 != 0) {
        error("Install failed on Prod1, please check")
        }
        sh(script: "systemctl start python-webserver.service")
    }
    node ("Nginx") {
        def Check2 = sh(script: "curl http://192.168.1.190:80/",returnStatus: true)
        if (Check2 != 0) {
        //notify to telegram error1
        error("Your application is not running, please revert")
        }
        //switch traffic to 2 Servers
        sh(script: "sed 's/#server 192.168.1.190/server 192.168.1.190/g' /etc/nginx/nginx.conf")
        sh(script:"systemctl reload nginx")
    }
}

stage('Deploy on second production node') {
    node ("Jenkin-node") {
        sh(script: "scp /var/lib/jenkins/workspace/${pkgName} root@192.168.1.191:/root")
    }
    node ("Nginx") {
        // switch all traffic to Prod1
        sh(script: "sed 's/server 192.168.1.191/#server 192.168.1.191/g' /etc/nginx/nginx.conf")
        sh(script:"systemctl reload nginx")
    }
    node ("prod2") {
        def Ins3 = sh(script: "dpkg -i /root/${pkgName}",returnStatus: true)
        if (Ins3 != 0) {
        error("Install failed on Prod2, please check")
        }
        sh(script: "systemctl start python-webserver.service")
    }
    node ("Nginx") {
        def Check3 = sh(script: "curl http://192.168.1.191:80/",returnStatus: true)
        if (Check3 != 0) {
        error("Your application is not running, please revert")
        }
        //switch traffic to 2 Servers
        sh(script: "sed 's/#server 192.168.1.191/server 192.168.1.190/g' /etc/nginx/nginx.conf")
        sh(script:"systemctl reload nginx")
    }
}
