// higher-order component (HOC) is a pattern used to share common functionality between components without repeating code.
// A higher-order component is actually not a component though, it is a function.
// A HOC function takes a component as an argument and returns a component.
// It transforms a component into another component and adds additional data or functionality.

var newData = {
    data: 'Data from HOC...',
};

var MyHOC = (WrappedComponent) => {
    class HOC extends React.Component {
        componentDidMount() {
            this.setState({
                data: newData.data,
                num: 12
            });
        }

        render() {
            return <WrappedComponent {...this.props} {...this.state} />;
        }
    }
    return HOC;
};

class MyComponent extends React.Component {
    render() {
        return (
            <div>
                {/*atttributes in state of HOC turn into attributes in props of Wrapped Component*/}
                <h1>{this.props.data} + {this.props.num}</h1>
            </div>
        )
    }
}

const Wrap = MyHOC(MyComponent);

ReactDOM.render(
  <div>
      <Wrap/>
  </div>
  ,
  document.getElementById('root')
);