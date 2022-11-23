import {Link} from "react-router-dom";
import {Container, Navbar, NavLink} from "react-bootstrap";

const HeaderLink = ({page, display, selected}) => {
    const title = page.charAt(0).toUpperCase() + page.slice(1);
    return <Link to={`/${page}`} className='headerlink-title'>
        {display || title}
    </Link>;
};

export default function Header() {
    let page = window.location.href.replace('http://localhost:3000/', '');
    if (page === 'http://localhost:3000/') {
        page = 'day';
    }
    return (
        <nav className="navbar navbar-dark bg-primary">
            <Container>
                <Navbar.Brand>
                    Jokes
                </Navbar.Brand>
                <Navbar.Text>
                    <div>
                        <br/>
                        <div className='header'>
                            <HeaderLink page='day' selected={page === 'day'}/>
                            <NavLink page='teams' selected={page === 'home'}/>
                        </div>
                    </div>
                </Navbar.Text>
            </Container>
        </nav>

    );
};