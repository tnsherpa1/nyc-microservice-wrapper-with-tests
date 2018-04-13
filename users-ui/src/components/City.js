import React, {Component} from 'react'
import Star from '../images/city_star.svg'
import axios from 'axios'

class City extends Component {
    state = {
        projects: []
    }

    createProject = async(project, index) => {
        try {
          const newProjectResponse = await axios.post(`/projects`, project)
          const updatedProjectsList = [...this.state.projects]
          updatedProjectsList.push(newProjectResponse.data)
          this.setState({projects: updatedProjectsList})
        } catch(error) {
          console.log('Error creating new project!')
          console.log(error)
        }
      }
    render() {
        return (
            <ul className="city_box">
                <li className="star-box" onClick={this.createProject}><img className='star' src={Star} alt="Star your project" /> Star</li>
                <li>Contact Name: {this.props.contact_name}</li>
                <li>Agency Name: {this.props.name}</li>
                <li>Summary: {this.props.title}</li>
                <li>Email: {this.props.contact_email}</li>
                <li>Tel: {this.props.contact_tel}</li>
                <li>Start Date: {this.props.start_date}</li>
            </ul>
        )   
    }
}

export default City