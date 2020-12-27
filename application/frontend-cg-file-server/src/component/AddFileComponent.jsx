import React, { Component } from 'react';
import FileService from '../service/FileService';

class AddFileComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            selectedFile: null,
            successUpload: null,
            failedUpload: null,
            message: null
        }
    }

    onFileChange = event => {
        this.setState({
            selectedFile: event.target.files[0],
            successUpload: null,
            failedUpload: null,
            message: null
        });
    };

    onFileUpload = () => {
        this.setState({
            successUpload: null,
            failedUpload: null,
            message: null
        });

        if (this.state.selectedFile) {
            const formData = new FormData();
            formData.append(
                "file",
                this.state.selectedFile,
                this.state.selectedFile.name
            );

            FileService.uploadFile(formData)
                .then(
                    response => {
                        this.setState({
                            successUpload: true,
                            message: response.data.message
                        });
                    }
                ).catch(error => {
                    this.setState({
                        failedUpload: true,
                        message: error.response.data.message
                    });
                });
        }
    };

    fileData = () => {
        if (this.state.selectedFile) {
            return (
                <div>
                    <h2>File Details:</h2>
                    <p>File Name: {this.state.selectedFile.name}</p>
                    <p>File Type: {this.state.selectedFile.type}</p>
                    <p>
                        Last Modified:{" "}
                        {this.state.selectedFile.lastModifiedDate.toDateString()}
                    </p>
                </div>
            );
        } else {
            return (
                <div>
                    <br />
                    <h4>Choose before Pressing the Upload button</h4>
                </div>
            );
        }
    };

    render() {
        return (
            <div class="container">
                <div className="landing">
                    <h2>
                        File Upload
                    </h2>
                    <div>
                        <input type="file" onChange={this.onFileChange} />
                        <button onClick={this.onFileUpload}>
                            Upload
                    </button>
                        {this.fileData()}
                        {this.state.successUpload && <div class="alert alert-success">{this.state.message}</div>}
                        {this.state.failedUpload && <div class="alert alert-danger">{this.state.message}</div>}
                    </div>
                    <div>
                        <button
                            className="button icon-left"
                            onClick={this.props.history.goBack}>
                            Back
                    </button>
                    </div>
                </div>
            </div>
        )
    }
}

export default AddFileComponent
