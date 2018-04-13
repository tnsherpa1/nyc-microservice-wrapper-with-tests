import React, {Component} from 'react'
import Api from './Api'

class Search extends Component {
    state = {
        value: ''
    }
    handleChange = (e) => {
        this.setState({
            value: e.target.value
        })
    }
    handleSubmit = (e) => {
        e.preventDefault()
    }
    render() {
        return (            
        <div>
            <form onSubmit={this.handleSubmit}>
            <li className="form-row">
                <input autoFocus={true} type="text" placeholder="Search" onChange={this.handleChange} value={this.state.value}/>
                <button className="button" type="submit">Search</button>
            </li>
            </form>
            <Api/>
        </div>
        )
    }
}

export default Search