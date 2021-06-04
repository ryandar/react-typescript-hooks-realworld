import {
  AppBar,
  Button,
  Icon,
  IconButton,
  TextField,
  Toolbar,
  Typography,
} from "@material-ui/core";
import MenuIcon from "@material-ui/icons/Menu";
import React, { Fragment, useState } from "react";
import CreateIcon from "@material-ui/icons/Create";
import SettingsIcon from "@material-ui/icons/Settings";
import "./Login.css";

export default function Login() {
  // handle email/password/loading/conditions
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [errors, setErrors] = useState("");

  // use redux
  // if we find user existing, then redirect to home page

  return (
    <Fragment>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" className="title">
            Conduit
          </Typography>
          <Button color="inherit">Home</Button>
          <Button color="inherit">
            <CreateIcon />
            New Article
          </Button>
          <Button color="inherit">
            <SettingsIcon />
            Settings
          </Button>
          <Button color="inherit">Login</Button>
        </Toolbar>
      </AppBar>

      <div className="border">
        <form className="login-form">
          <TextField required id="standard-required" label="Email" />
          <TextField required id="standard-required" label="Password" />
          <Button className='login-button' variant="contained" size="small" color="primary">
            Login
          </Button>
        </form>
      </div>
    </Fragment>
  );
}
