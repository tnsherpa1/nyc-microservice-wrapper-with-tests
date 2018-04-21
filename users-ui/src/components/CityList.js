import React, {Component} from 'react'
import CityNewForm from './CityNewForm'
import axios from 'axios'

class CityList extends Component {
    state = {
        projects: []
    }

    createProject = async(project, index) => {
        try {
          const newProjectResponse = await axios.post(process.env.REACT_APP_HOST+`/projects/`, project)
          const updatedProjectResponse = [...this.state.projects]
          updatedProjectResponse.push(newProjectResponse.data)
          this.setState({projects: updatedProjectResponse})
        } catch(error) {
          console.log("error creating new project")
          console.log(error)
        }
      }
    render() {
        const listComponents = this.props.listings.map((listing, index)=>{
            return <CityNewForm createNewProject={this.createProject}
                         agency_name={listing.agency_name} 
                         summary={listing.short_title}
                         contact_name={listing.contact_name}
                         contact_email={listing.email}
                         contact_tel={listing.contact_phone}
                         start_date={listing.start_date}
                         key={index}
                         />
        })
        return (
            <div>
            {listComponents}
            </div>
        )
    }
}

export default CityList