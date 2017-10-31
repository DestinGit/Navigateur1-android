
USE cours;

CREATE TABLE `communes` (
  `id_commune` int(11) NOT NULL,
  `nom_commune` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cp_commune` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `departement_commune` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `insee_commune` varchar(5) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--


-- Index pour la table `communes`
--
ALTER TABLE `communes`
  ADD PRIMARY KEY (`id_commune`),
  ADD UNIQUE KEY `idx_u_insee_commune` (`insee_commune`),
  ADD KEY `idx_nom_commune` (`nom_commune`),
  ADD KEY `idx_insee_commune` (`insee_commune`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `communes`
--
ALTER TABLE `communes`
  MODIFY `id_commune` int(11) NOT NULL AUTO_INCREMENT;
