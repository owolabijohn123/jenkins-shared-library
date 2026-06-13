def call(Map config) {
    // Update deployment YAML files
    sh """
        # Update main app image
        sed -i "s|image: .*easyshop-app.*|image: dreedsir12/easyshop-app:${config.imageTag}|" ${config.manifestsPath}/08-easyshop-deployment.yaml
        # Update migration job image (if exists)
        if [ -f ${config.manifestsPath}/12-migration-job.yaml ]; then
            sed -i "s|image: .*easyshop-migration.*|image: dreedsir12/easyshop-migration:${config.imageTag}|" ${config.manifestsPath}/12-migration-job.yaml
        fi
    """
    // Commit & Push changes securely
    withCredentials([usernamePassword(
        credentialsId: config.gitCredentials,
        usernameVariable: 'GIT_USER',
        passwordVariable: 'GIT_PASS'
    )]) {
        sh """
            git config user.name "${config.gitUserName}"
            git config user.email "${config.gitUserEmail}"
            git add ${config.manifestsPath}/*.yaml
            git commit -m "Update image tag to ${config.imageTag}" || echo "No changes to commit"
            git push https://${GIT_USER}:${GIT_PASS}@github.com/owolabijohn123/ecommerce-project.git HEAD:master
        """
    }
}
