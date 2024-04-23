package hu.bme.mit.gamma.architecture.transformation.enterprisearchitect.utils

import java.text.Normalizer

class NamingUtils {
	static def simplifyName(String name) {
		var simplifiedName = name.replace(".", "_")
		simplifiedName = Normalizer.normalize(simplifiedName, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
		simplifiedName = simplifiedName.replace('Ö', 'O')
		simplifiedName = simplifiedName.replace('Ő', 'O')
		simplifiedName = simplifiedName.replace('Ű', 'U')
		simplifiedName = simplifiedName.replace('Ü', 'U')
		simplifiedName = simplifiedName.replace('Á', 'A')
		simplifiedName = simplifiedName.replace('É', 'E')
		simplifiedName = simplifiedName.replace('ö', 'o')
		simplifiedName = simplifiedName.replace('ő', 'o')
		simplifiedName = simplifiedName.replace('ű', 'u')
		simplifiedName = simplifiedName.replace('ü', 'u')
		simplifiedName = simplifiedName.replace('á', 'a')
		simplifiedName = simplifiedName.replace('é', 'e')
		// replace formbidden characters
		val forbiddenCharacters = #["-", ":", " ", "(", ")", "{", "}", "[", "]", "{", "}", ",", ";", ";", "%", "!", "&",
			"/", "+"]
		for (c : forbiddenCharacters) {
			simplifiedName = simplifiedName.replace(c, "_")
		}
		// remove double '_'-s
		while (simplifiedName.contains("__")) {
			simplifiedName = simplifiedName.replaceAll("__", "_")
		}
		// first digit character checking
		if (simplifiedName.matches("^[0-9]")) {
			simplifiedName = "_" + simplifiedName
		}
		return simplifiedName
	}

}
