import React,{Component} from 'react';
import LinearProgress from '@material-ui/core/LinearProgress';


class UploadFileComponent extends Component {

	constructor(props) {
		super(props);
		this.state = {
			selectedFile: null,
			isFileSelected: false,
			fileUploadInProgress: false,
			fileUploadDone: false,
		}
	}
	
	onFileChange = event => {
		this.setState({ selectedFile: event.target.files[0] });
		this.setState({ isFileSelected: true });
	};
	
	onFileUpload = () => {
		const formData = new FormData();
		formData.append(
			"myFile",
			this.state.selectedFile,
			this.state.selectedFile.name
		);
		
		console.log(this.state.selectedFile);
		this.setState({fileUploadInProgress : true});
		this.setState({fileUploadDone: false});
		
		// call file upload api
		fetch(
			'http://localhost:8080/api/upload',
			{
				method: 'POST',
				body: formData,
			}
		).then(result => {
			console.log('File uploaded successfully');
			this.setState({fileUploadDone: true});
			this.setState({fileUploadInProgress : false});
			this.props.handleStateChange(true);
		});
		// Request made to the backend api
		// Send formData object
	};
	
	// File content to be displayed after
	// file upload is complete
	fileData = () => {
	
	if (this.state.selectedFile) {
		
		return (
		<div>
			<span>File Name: {this.state.selectedFile.name}</span>
		</div>
		);
	} else {
		return (
		<span></span>
		);
	}
	};
	
	render() {

		 const disableUpload = this.state.isFileSelected;
		 const { fileUploadDone, fileUploadInProgress } = this.state;
		return (
			<div className="fileUpload">
				<h5>Please Upload Orders CSV file </h5>
					<input type="file" onChange={this.onFileChange} />
					
					<button onClick={this.onFileUpload} disabled={!disableUpload}>
					Upload!
					</button>

					<div className="fileUploadSuccess" >
						{ fileUploadInProgress ? <LinearProgress /> : null }	
						{ fileUploadDone ? <h5> File uploaded successfully </h5> : null }
					</div>
			</div>
		);
	}
}

export default UploadFileComponent;
