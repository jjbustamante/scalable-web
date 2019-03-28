#!/usr/bin/env groovy

pipeline {
    agent {
        node {
            label "firstrain"
        }
    } 
    options {
        timestamps()
    }
    stages {
        stage('Build') {
            steps {
                retry(3) {
                    dir(".") {
                        sh "mvn test"
                    }
                }
            }
        }
    }
}
