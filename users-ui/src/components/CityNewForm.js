import React, {Component} from 'react'
import Star from '../images/city_star.svg'

class CityNewForm extends Component {
    state = {
        newProject: {}
    }
    componentDidMount(){
        const createdNewProject = {...this.state.newProject}
        createdNewProject["contactName"] = this.props.contact_name
        createdNewProject["agencyName"] = this.props.agency_name
        createdNewProject["summary"] = this.props.summary
        createdNewProject["email"] = this.props.contact_email
        createdNewProject["tel"] = this.props.contact_tel
        createdNewProject["startDate"] = this.props.start_date
        this.setState({
          newProject: createdNewProject
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
                    <button className="search-button" type="submit"><img className='star' src={Star} alt="Star your project" /> Star</button>
                </li>
                <li className="form-row">
                    <label className="search-header" htmlFor="Contact Name">Contact Name</label> 
                    <input className="search-input" autoComplete="off" name="contactName" value={this.props.contact_name} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="search-header" htmlFor="Agency Name">Agency Name</label> 
                    <input className="search-input" autoComplete="off" name="agencyName" value={this.props.agency_name} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="search-header" htmlFor="Summary">Summary</label>
                    <input className="search-input" autoComplete="off" name="summary" value={this.props.summary} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="search-header" htmlFor="Email">Email</label>
                    <input className="search-input" autoComplete="off" name="email" value={this.props.contact_email} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="search-header" htmlFor="Tel">Tel</label>
                    <input className="search-input" autoComplete="off" name="tel" value={this.props.contact_tel} onChange={this.handleChange} />
                </li>
                <li className="form-row">
                    <label className="search-header" htmlFor="Start Date">Start Date</label>
                    <input className="search-input" autoComplete="off" name="startDate" value={this.props.start_date} onChange={this.handleChange} />
                </li>
            </form>
            </div>
        )   
    }
}

export default CityNewForm