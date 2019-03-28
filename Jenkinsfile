#!/usr/bin/env groovy

pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn test"    
            }
        }
    }
}
