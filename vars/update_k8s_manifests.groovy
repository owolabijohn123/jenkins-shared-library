
def call(Map config) {
    sh """
        sed -i 's|image:.*|image: dreedsir12/easyshop-app:${config.imageTag}|' ${config.manifestsPath}/deployment.yaml
        git config user.name "${config.gitUserName}"
        git config user.email "${config.gitUserEmail}"
        git add .
        git commit -m "Update image tag to ${config.imageTag}"
        git push https://${config.gitCredentials}@github.com/yinkaowolabi091-web/e-commerce-app-devops-project.git HEAD:master
    """
}
