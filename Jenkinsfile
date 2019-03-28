#!/usr/bin/env groovy

pipeline {
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
