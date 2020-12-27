import React, { Component } from 'react';
import FileStorageServerApp from './component/FileStorageServerApp';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import ListFilesComponent from './component/ListFilesComponent';
import AddFileComponent from './component/AddFileComponent';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route exact path="/" component={FileStorageServerApp} />
          <Route exact path="/list-files" component={ListFilesComponent} />
          <Route exact path="/add-file" component={AddFileComponent} />
        </Switch>
      </Router>
    )
  }
}

export default App;
