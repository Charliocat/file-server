import FileService from "../service/FileService";

const { Component } = require("react");

class ListFilesComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            files: [],
            message: null
        }
        this.refreshFiles = this.refreshFiles.bind(this)
        this.deleteFileClicked = this.deleteFileClicked.bind(this)
    }

    componentDidMount() {
        this.refreshFiles();
    }

    refreshFiles() {
        FileService.retriveAllFiles()
            .then(
                response => {
                    console.log(response);
                    this.setState({ files: response.data })
                }
            )
    }

    deleteFileClicked(name) {
        FileService.deleteFile(name)
            .then(
                response => {
                    this.setState({ message: `Deleted file ${name} Successful` })
                    this.refreshFiles()
                }
            )
    }

    render() {
        return (
            <div>
                <div className="container">
                    <h3>Files</h3>
                </div>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>FileName</th>
                                <th>Path</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.files.map(
                                    file =>
                                        <tr key={file.name}>
                                            <td>{file.name}</td>
                                            <td>{file.path}</td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteFileClicked(file.name)}>Delete</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <div className="container">
                    <button
                        className="button icon-left"
                        onClick={this.props.history.goBack}>
                        Back
                    </button>
                </div>
            </div>
        )
    }
}

export default ListFilesComponent

