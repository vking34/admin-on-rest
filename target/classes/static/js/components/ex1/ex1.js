
class Ex1 extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            num: this.props.defaultNum
        };
        this.addOne = this.addOne.bind(this);
    }
    render(){
        return(
            <div>
                <button onClick={this.addOne} >{this.props.text}</button>
                <Num counter={this.state.num}></Num>

            </div>
        );
    }
    addOne(){
        // this.state.num = parseInt(this.state.num) + 1;
        // this.setState(this.state, this.callback);
        this.setState({
           num : parseInt(this.state.num) + 1
        });
    }
}

class Num extends React.Component{

    componentWillMount(){
        console.log('Num component WILL MOUNT.');
    }

    componentDidMount(){
        console.log('Num component DID MOUNT.');
        setTimeout(() => {
            ReactDOM.unmountComponentAtNode(window.element1);
        }, 10000);
    }

    componentWillReceiveProps(newProps){
        console.log('Num component WILL RECEIVE PROPS.');
    }

    shouldComponentUpdate(newProps, newState){
        console.log('Num component SHOULD COMPONENT UPDATE?');
        return true;
    }

    componentWillUpdate(nextProps, nextState){
        console.log("Num component WILL UPDATE.");
    }

    componentDidUpdate(preProps, preState){
        console.log("Num component UPDATED.");
    }

    componentWillUnmount(){
        console.log("Num component WILL UNMOUNT.");
    }

    render(){
        return(
            <div>
                <h3>{this.props.counter}</h3>
            </div>
        );
    }
}

class Ex2 extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            text : "init text..."
        };

        this.updateText = this.updateText.bind(this);
        this.clearText = this.clearText.bind(this);
    }

    updateText(e){
        this.setState({
            text: e.target.value
        });
    }

    clearText(){
        this.setState({
           text: ''
        });
        ReactDOM.findDOMNode(this.refs.textInput).focus();
    }

    render(){
        return(
            <div>
                <ChildEx2 text={this.state.text} update={this.updateText} clear={this.clearText}></ChildEx2>
            </div>
        );
    }
}

class ChildEx2 extends React.Component{
    render(){
        return(
            <div>
                <input ref= "textInput" type="text" value={this.props.text} onChange={this.props.update}/>
                <button onClick={this.props.clear}>Clear</button>
                <h4>Result: {this.props.text}</h4>

            </div>
        );
    }
}

window.element1 = document.getElementById('ex1');
window.element2 = document.getElementById('ex2');

Ex1.defaultProps = {
  text: "Increase"
};

ReactDOM.render(
    <Ex1 defaultNum="10" />,
    element1
);

ReactDOM.render(
    <div>
        <Ex2></Ex2>
    </div>
    ,
    element2
);

