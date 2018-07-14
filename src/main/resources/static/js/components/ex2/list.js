class Seacrh extends React.Component{

    render(){
        return(
            <div className="row">
                <div className="col-xs-6"></div>
                <div className="col-xs-6">
                    <form action="/departments/search/" method="get">
                        <div className="input-group">
                            <input type="text" className="form-control" placeholder={this.props.text} name="title"
                                   autoComplete="off"/>
                            <div className="input-group-btn">
                                <button className="btn btn-default" type="submit"><i className="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <br/>
                </div>
            </div>
        );
    }
}

class Title extends React.Component{
    render(){
        return(
            <div>
                <h1>{this.props.children}</h1>
            </div>
        );
    }
}

class List extends React.Component{

    constructor(props)
    {
        super(props);

        this.state = {
            departments : [],
            text: "List"
        };

        this.removeDepartment = this.removeDepartment.bind(this);
        this.addDpartment = this.addDpartment.bind(this);
    }

    componentDidMount(){
        fetch("http://localhost/rest-api/departments")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        departments: result
                    });

                    console.log(this.state.departments.toString());
                }
            );
    }

    removeDepartment(id, index) {
        fetch('/rest-api/departments/' + id, { method: 'DELETE'})
            .then(res => res.json())
            .then((result) => {
                    if(result.status === true){
                        this.state.departments.splice(index, 1);
                        for(let i = 0; i < this.state.departments.length; i++){
                            console.log(this.state.departments[i].title);
                        }
                        this.setState({
                            departments: this.state.departments
                        });
                    }
                    else {
                        alert("Error");
                    }
                }
            );
    }

    addDpartment(){
        var department = new Object();
        department.department_id = $('#newID_add').val();
        department.title = $("#newTitle_add").val();
        department.sub_title = $("#newSubTitle_add").val();
        department.numOfEmployee = 0;

        fetch('/rest-api/departments', {
            method: 'POST',
            headers: {
                'Accept': 'application/json, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(department)
        })
            .then(res => res.json())
            .then((result) => {
                if(result.status === true)
                {
                    $("#close_add").click();
                    alert("Successful");
                    this.state.departments.push(department);
                    this.setState({
                        departments: this.state.departments
                    })
                }
                else {
                    alert("Failed !");
                }
            });

    }

    render(){
        return(
            <div>
                <div>{this.state.text}</div>
                <div className="row">
                    <div className="col-xs-1" style={{backgroundColor: "lavender"}}>Order</div>
                    <div className="col-xs-2" style={{backgroundColor: "lavenderblush"}}>Department ID</div>
                    <div className="col-xs-2" style={{backgroundColor: "lavender"}}>Title</div>
                    <div className="col-xs-3" style={{backgroundColor: "lavenderblush"}}>Sub-title</div>
                    <div className="col-xs-2" style={{backgroundColor: "lavender"}}>Number of members</div>
                    <div className="col-xs-1" ></div>
                    <div className="col-xs-1" ></div>
                </div>

                <div>
                    {this.state.departments.map((department, i) =>
                        <Department key={i} ord={i} info={department} remove={this.removeDepartment}/>
                    )
                    }
                </div>
                <Options add={this.addDpartment}/>
            </div>
        );
    }
}

class Department extends React.Component{

    handleRemove(){
        var {ord, remove} = this.props;
        remove(this.props.info.department_id, ord);
    }

    render(){
        return(
            <div className="row">
                <div className="col-xs-1" style={{backgroundColor: "lavender"}}>{this.props.ord}</div>
                <div className="col-xs-2" style={{backgroundColor: "lavenderblush"}}>{this.props.info.department_id}</div>
                <div className="col-xs-2" style={{backgroundColor: "lavender"}}>{this.props.info.title}</div>
                <div className="col-xs-3" style={{backgroundColor: "lavenderblush"}}>{this.props.info.sub_title}</div>
                <div className="col-xs-2" style={{backgroundColor: "lavender"}}>{this.props.info.numOfEmployee}</div>
                <div className="col-xs-1" >
                    <button className="btn btn-default" type="submit"><i className="fas fa-edit"></i></button>
                </div>
                <div className="col-xs-1">
                    <button className="btn btn-default" type="submit" onClick={this.handleRemove.bind(this)}><i className="fas fa-trash-alt"></i></button>
                </div>
            </div>
        );
    }
}

class Options extends React.Component{

    render(){
        return(
            <div className="row">
                <div className="col-xs-4">
                    <button id="add_department_btn" type="button" className="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" ><i className="fas fa-plus-circle"></i>  Add Department
                    </button>

                    <div className="modal fade" id="myModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                    <h4 className="modal-title" id="myModalLabel">Add Department</h4>
                                </div>
                                <div className="modal-body">

                                    <div className="input-group">
                                        <span className="input-group-addon" id="basic-addon1_add">Department ID</span>
                                        <input ref="id_add" id="newID_add" type="number" className="form-control"
                                               placeholder="Enter Department ID" aria-describedby="basic-addon1"/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon" id="basic-addon2_add">Title</span>
                                        <input ref="title_add" id="newTitle_add" type="text" className="form-control"
                                               placeholder="Enter new title" aria-describedby="basic-addon1"/>
                                    </div>
                                    <br/>
                                    <div className="input-group">
                                        <span className="input-group-addon" id="basic-addon3_add">Sub-Title</span>
                                        <input ref="subtitle_add" id="newSubTitle_add" type="text" className="form-control"
                                               placeholder="Enter Sub title" aria-describedby="basic-addon1"/>
                                    </div>

                                </div>
                                <div className="modal-footer">
                                    <button id="close_add" type="button" className="btn btn-default"
                                            data-dismiss="modal">Close
                                    </button>
                                    <button id="submit_add_btn" type="button" className="btn btn-primary"
                                            onClick={this.props.add}>Add Department
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

ReactDOM.render(
    <div className="container">
        <Title>Department List</Title>
        <br/>
        <Seacrh text="Search by title"/>
        <List/>
    </div>
    ,
    document.getElementById('root')
);