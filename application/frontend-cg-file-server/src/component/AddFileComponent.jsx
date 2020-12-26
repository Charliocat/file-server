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

    // On file select (from the pop up) 
    onFileChange = event => {
        // Update the state 
        this.setState({
            selectedFile: event.target.files[0],
            successUpload: null,
            failedUpload: null,
            message: null
        });
    };

    // On file upload (click the upload button) 
    onFileUpload = () => {
        if (this.state.selectedFile) {
            // Create an object of formData 
            const formData = new FormData();
            // Update the formData object 
            formData.append(
                "file",
                this.state.selectedFile,
                this.state.selectedFile.name
            );

            // Details of the uploaded file 
            console.log(this.state.selectedFile);

            // Request made to the backend api 
            // Send formData object
            FileService.uploadFile(formData)
                .then(
                    response => {
                        this.setState({
                            successUpload: true,
                            message: `Uploaded file ${this.state.selectedFile.name} Successful`
                        });
                    }
                ).catch(error => {
                    console.log(error)
                    this.setState({
                        failedUpload: true,
                        message: `File ${this.state.selectedFile.name} not uploaded`
                    });
                });
        }
    };

    // File content to be displayed after 
    // file upload is complete 
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
