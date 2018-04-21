import React, {Component} from 'react'
import Star from '../images/city_star.svg'
import axios from 'axios'

class CityNewForm extends Component {
    state = {
        newProject: {}
    }
    componentDidMount(){
        const updatedNewProject = {...this.state.newProject}
        updatedNewProject["contactName"] = this.props.contact_name
        updatedNewProject["agencyName"] = this.props.agency_name
        updatedNewProject["summary"] = this.props.summary
        updatedNewProject["email"] = this.props.contact_email
        updatedNewProject["tel"] = this.props.contact_tel
        updatedNewProject["startDate"] = this.props.start_date
        this.setState({
          newProject: updatedNewProject
        })
    }
    handleChange = (e) => {
        const attributeToChange = e.target.name
        const newValue = e.target.value
        const updatedNewProject = {...this.state.newProject}
        updatedNewProject[attributeToChange] = newValue
        this.setState({
          newProject: updatedNewProject
        })
    }
    handleSubmit = (e) => {
        e.preventDefault()
        this.props.createNewProject(this.state.newProject)
    }

    render() {
        return (
            <div>
            <hr/>
            <form onSubmit={this.handleSubmit}>
                <li className="form-row btn-row">
                    <button className="button" type="submit"><img className='star' src={Star} alt="Star your project" /> Star</button>
                </li>
                <li className="form-row">
                    <label className="header" htmlFor="Contact Name">Contact Name</label> 
                    <input autoComplete="off" name="contactName" value={this.props.contact_name} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="header" htmlFor="Agency Name">Agency Name</label> 
                    <input autoComplete="off" name="agencyName" value={this.props.agency_name} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="header" htmlFor="Summary">Summary</label>
                    <input autoComplete="off" name="summary" value={this.props.summary} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="header" htmlFor="Email">Email</label>
                    <input autoComplete="off" name="email" value={this.props.contact_email} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="header" htmlFor="Tel">Tel</label>
                    <input autoComplete="off" name="tel" value={this.props.contact_tel} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="header" htmlFor="Start Date">Start Date</label>
                    <input autoComplete="off" name="startDate" value={this.props.start_date} onChange={this.handleChange} />
                </li>
            </form>
            </div>
        )   
    }
}

export default CityNewForm