import React, { Component } from 'react';

class FileStorageServerApp extends Component {
    constructor(props) {
        super(props)
        this.nextPath = this.nextPath.bind(this)
    }

    nextPath(id) {
        console.log(id)
        this.props.history.push(`${id}`)
    }

    render() {
        return (
            <div class="container">
                <div className="lander" >
                <h1>File Storage Application</h1>
                    <p>A simple app showing react button click navigation</p>
                    <button onClick={() => this.nextPath('/add-file')}>
                        Add file
                    </button>
                    <button onClick={() => this.nextPath('/list-files')}>
                        List files
                    </button>
                </div>
            </div>
        )
    }
}

export default FileStorageServerApp
