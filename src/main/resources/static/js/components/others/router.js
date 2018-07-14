
const Router = ReactRouterDOM.BrowserRouter;
const Route = ReactRouterDOM.Route;
const Link = ReactRouterDOM.Link;
const Switch = ReactRouterDOM.Switch;

class About extends React.Component{
    render(){
        return(
            <div>
                <h2>About</h2>
            </div>
        );
    }
}

class Login extends React.Component{
    render(){
        return(
            <div>
                <h2>Login</h2>
            </div>
        );
    }
}

class MainFrag extends React.Component{
    render(){
        return(
            <Router>
                <div>
                    <h2>Main Fragment</h2>

                    <ul>
                        <li><Link to={'/react/router/login'}>Login</Link></li>
                        <li><Link to={'/react/router/about'}>About</Link></li>
                    </ul>

                    <hr/>

                    <Switch>
                        <Route exact path='/react/router/login' component={Login}/>
                        <Route exact path='/react/router/about' component={About}/>
                    </Switch>

                </div>

            </Router>
        );
    }
}


ReactDOM.render(
    <MainFrag/>,
    document.getElementById('root')
);