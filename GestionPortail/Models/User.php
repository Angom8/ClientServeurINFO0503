<?php

class User {

    private string $username;
    private string $password;
    private int $userType;

    public function __construct(string $username, string $password, int $userType){
        $this->password= $password;
        $this->username = $username;
        $this->userType = $userType;
    }

    public function getUsername() : string{
        return $this->username;
    }

    public function getPassword() : string{
        return $this->password;
    }

    public function getType() : int {return $this->userType;}
}

?>
