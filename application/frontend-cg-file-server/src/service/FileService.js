import axios from 'axios'

const API_URL = 'http://localhost:8091'
const V1_FILES = `${API_URL}/v1/files`

class FileService {
    retriveAllFiles() {
        return axios.get(`${V1_FILES}`)
    }

    deleteFile(name) {
        return axios.delete(`${V1_FILES}/${name}`)
    }

    uploadFile(file) {
        return axios.post(`${V1_FILES}`, file)
    }

}

export default new FileService()
