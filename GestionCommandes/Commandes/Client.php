<?php
/**
 * @author Antoine Thebault
 */

class Client implements JsonSerializable
{

    private string $nom;
    private string $prenom;
    private int $id;

    public function __construct(string $nom, string $prenom, int $id)
    {
        $this->nom = $nom;
        $this->prenom = $prenom;
        $this->id = $id;
    }

    public function getName(): string
    {
        return $this->nom;
    }

    public function getPrenom(): string
    {
        return $this->prenom;
    }

    public function getId(){
        return $this->id;
    }

    public function jsonSerialize()
    {
        return [
            'nom' => $this->nom,
            'prenom' => $this->prenom,
            'id' => $this->id,
        ];
    }

}