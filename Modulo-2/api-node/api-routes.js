//Arquivo que contém as rotas o api

// Initialize express router
let router = require('express').Router();


// Set default API response
router.get('/', function (req, res) {
    res.status(200);
    res.json({
        status: 'API Its Working',
        message: 'REST API Nível de maturidade 2 (Richardson)',
    });
});

// Import contact controller
var contactController = require('./contact-controller');

// Contact routes
router.route('/contacts')
    .get(contactController.index)
    .post(contactController.new);

router.route('/contacts/:contact_id')
    .get(contactController.view)
    .patch(contactController.update)
    .put(contactController.update)
    .delete(contactController.delete);

// Export API routes
module.exports = router;