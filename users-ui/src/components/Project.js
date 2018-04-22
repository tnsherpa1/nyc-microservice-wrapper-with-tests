import React, {Component} from 'react'

class Project extends Component {
    render() {
        return (
            <div>
            <ul>
                <li>Agency Name: {this.props.agency_name}</li>
                <li>Contact Name: {this.props.contact_name}</li>
                <li>Summary: {this.props.summary}</li>
                <li>Email: {this.props.email}</li>
                <li>Tel: {this.props.tel}</li>
                <li>Start Date: {this.props.start_date}</li>
            </ul>
            <hr/>>
            </div>
        )
    }
}

export default Project