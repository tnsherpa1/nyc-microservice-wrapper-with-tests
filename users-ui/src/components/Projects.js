import React, { Component } from 'react'
import axios from 'axios'
import Project from './Project'

class Projects extends Component {
    state = {
        projects: []
    }
    async componentDidMount() {
        try {
            const projects = await axios.get(process.env.REACT_APP_HOST+`/projects/`)
            this.setState({ projects: projects.data})
        } catch(e) {
            console.log(e)
        }
    }
    render(){
        const projectDisplay = this.state.projects.map((project)=>{
            return <Project agency_name={project.agencyName}
                     contact_name={project.contactMame}
                     summary={project.summary}
                     email={project.email}
                     tel={project.tel}
                     start_date={project.startDate} />
        })
        return (
            <div>
                {projectDisplay}
            </div>
        )
    }
}

export default Projects