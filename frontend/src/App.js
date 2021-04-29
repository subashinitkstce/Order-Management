import React, { Component } from 'react';
import './App.css';
import ListOrdersComponent from "./components/ListOrdersComponent";
import UploadFileComponent from "./components/UploadFileComponent";

class App extends Component {
  constructor(props) {
    super(props);
    this.handleStateChange = this.handleStateChange.bind(this);
    this.state = {
      fileUploaded : 1
    }
  }
  
  
  handleStateChange(){
    const value = this.state.updatedKey;
    this.setState({ fileUploaded : value+1 });
  }

  render() {
    return (
      <div className="container">
        <UploadFileComponent handleStateChange = {this.handleStateChange} />
        
        <ListOrdersComponent key={this.state.fileUploaded} />
      </div>
    );
  }
}

export default App;
